package com.itheima.service;

import com.itheima.domain.SysRole;

import java.util.List;

public interface RoleService {
    List<SysRole> fingAllRole();

    void roleAddInfo(SysRole sysRole);

    SysRole findRoleById(Integer id);


    void updateRolePermission(Integer roleId, Integer[] ids);
}
