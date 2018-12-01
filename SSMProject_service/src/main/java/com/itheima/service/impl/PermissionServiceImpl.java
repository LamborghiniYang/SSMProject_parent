package com.itheima.service.impl;

import com.itheima.dao.PermissionDao;
import com.itheima.domain.SysPermission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    public List<SysPermission> findAllPermission() {
        return permissionDao.findAllPermission();
    }

    public void save(SysPermission peimission) {
        permissionDao.save(peimission);
    }
}
