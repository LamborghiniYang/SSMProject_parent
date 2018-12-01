package com.itheima.service.impl;

import com.itheima.dao.RoleDao;
import com.itheima.domain.SysRole;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<SysRole> fingAllRole() {
        return roleDao.findAllRole();
    }

    @Override
    public void roleAddInfo(SysRole sysRole) {
        roleDao.roleAddInfo(sysRole);
    }

    @Override
    public SysRole findRoleById(Integer id) {

        return  roleDao.findRoleById(id);

    }

    @Override
    public void updateRolePermission(Integer roleId, Integer[] ids) {
        roleDao.deleteRoleId(roleId);
        System.out.println(Arrays.toString(ids));
        if (ids!=null&&ids.length>0){
            for (Integer permissionId : ids) {
                roleDao.insertRoleIdAndPermissionId(roleId,permissionId);
            }

        }



    }


}
