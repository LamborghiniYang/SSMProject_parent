package com.itheima.dao;

import com.itheima.domain.SysPermission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {
    @Insert("insert into sys_permission  values (com_sequence.nextval,#{permissionName}, #{url})")
    public void save(SysPermission peimission);

    @Select("select * from sys_permission where id in(select permissionId from sys_role_permission where roleId=#{roleId})")
    List<SysPermission> findPermissionByroleId(Integer roleId);

    @Select("select * from sys_permission")
    List<SysPermission> findAllPermission();
}
