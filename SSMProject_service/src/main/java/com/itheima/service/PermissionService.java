package com.itheima.service;

import com.itheima.domain.SysPermission;

import java.util.List;

public interface PermissionService {
    List<SysPermission> findAllPermission();
     void save(SysPermission peimission);
}
