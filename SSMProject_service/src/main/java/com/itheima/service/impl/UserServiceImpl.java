package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.UserDao;
import com.itheima.domain.SysRole;
import com.itheima.domain.SysUser;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: wyan
 * @Date: 2018/11/26 18:02
 * @Description:
 */
@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    /*
     * 实现框架的UserDsetailsService覆写方法通过用户名获取user对象
     * 用于框架验证处理
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //通过username查询数据库得到用户
        SysUser sysUser = userDao.findUserByName(username);
        //应该通过当前的用户信息得到用户对应的角色，并把角色名称添加到角色集合中，用于与spring-sercurity安全框架设置的角色进行比较。
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        List<SysRole> sysRoleList = sysUser.getSysRoleList();
        for (SysRole sysRole : sysRoleList) {
            authorities.add(new SimpleGrantedAuthority(sysRole.getRoleName()));
        }
        User user = new User(sysUser.getUsername(), sysUser.getPassword(), true,sysUser.getStatus()==1,true,true,authorities);
        return user;

    }

    /**
     * 查询全部用户信息
     *
     * @return
     */

    @Override
    public PageInfo<SysUser> findAllUser(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> userList = userDao.findAllUser();
        PageInfo<SysUser> userPageInfo = new PageInfo<>(userList);
        return userPageInfo;

    }

    @Override
    public void userAddInfo(SysUser sysUser) {
        String newpwd = passwordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(newpwd);
        userDao.userAddInfo(sysUser);
    }

    @Override
    public SysUser userDetail(Integer userId) {

        return userDao.userDetail(userId);
    }

   /* @Override
    public void settingrUserRole(Integer userId) {
        userDao.settingrUserRole(userId);
    }*/

    @Override
    public SysUser findUserById(Integer id) {

        System.out.println(userDao.findUserById(id).getSysRoleList());
        return userDao.findUserById(id);
    }

    @Override
    public void updateUserRole(Integer userId, Integer[] ids) {
        userDao.deleteUserRole(userId);
        for (Integer id : ids) {
            userDao.insertUserRole(userId, id);
        }

    }

}
