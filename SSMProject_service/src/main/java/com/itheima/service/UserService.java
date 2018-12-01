package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService  extends UserDetailsService{
    PageInfo<SysUser> findAllUser(int pageNum, int pageSize);

    void userAddInfo(SysUser sysUser);

    SysUser userDetail(Integer userId);

    //void settingrUserRole(Integer userId);

    SysUser findUserById(Integer id);

   /* void insertUserRole(Integer userId, Integer[] ids);
    void deleteUserRole(Integer userId);*/
void updateUserRole(Integer userId, Integer[] ids);
}
