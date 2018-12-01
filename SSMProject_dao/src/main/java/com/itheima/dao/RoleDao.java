package com.itheima.dao;

import com.itheima.domain.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {
    @Select("select * from sys_role")
    List<SysRole> findAllRole();

    @Insert("insert into sys_role values(com_sequence.nextval,#{roleName},#{roleDesc})")
    void roleAddInfo(SysRole sysRole);

    @Select("select * from sys_role where id in (select roleid from sys_user_role where userid=#{id})")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "permissionList", javaType = List.class,many = @Many(select = "com.itheima.dao.PermissionDao.findPermissionByroleId"))
    })
    List<SysRole> findRolesByUserId(Integer id);

    @Select("select * from sys_role where id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "permissionList", javaType = List.class,many = @Many(select = "com.itheima.dao.PermissionDao.findPermissionByroleId"))
    })
    SysRole findRoleById(Integer id);

    @Select("delete from sys_role_permission where roleId=#{roleId}")
    void deleteRoleId(Integer roleId);

    @Insert("insert into sys_role_permission values(#{permissionId},#{roleId})")
    void insertRoleIdAndPermissionId(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);
}
