package com.itheima.dao;

import com.itheima.domain.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Auther: wyan
 * @Date: 2018/11/26 18:02
 * @Description:
 */
public interface UserDao {

    @Select("select * from sys_user where username=#{username}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "sysRoleList", javaType = List.class,
                    many = @Many(select = "com.itheima.dao.RoleDao.findRolesByUserId")
            )
    })
    SysUser findUserByName(String username);

    @Select("select * from sys_user")
    List<SysUser> findAllUser();

    @Insert("insert into sys_user values(com_sequence.nextval,#{username},#{email},#{password},#{phoneNum},#{status})")
    void userAddInfo(SysUser sysUser);

    @Select("select * from sys_user where id=#{userId}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "sysRoleList", javaType = List.class,
                    many = @Many(select = "com.itheima.dao.RoleDao.findRolesByUserId")
            )
    })
    SysUser userDetail(Integer userId);

    @Select("select * from sys_user where id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "sysRoleList", javaType = List.class,
                    many = @Many(select = "com.itheima.dao.RoleDao.findRolesByUserId")
            )
    })
    SysUser findUserById(Integer id);
@Insert("insert into sys_user_role values(#{userId},#{roleId})")
    void insertUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
@Delete("delete from sys_user_role where userid=#{userId}")
    void deleteUserRole(Integer userId);

    /*@Select("select * from sys_user where id=#{userId}")
    SysUser findUserById(Integer userId);*/

}
