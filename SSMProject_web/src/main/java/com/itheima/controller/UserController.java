package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.SysRole;
import com.itheima.domain.SysUser;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("User")
/*权限控制方式3种：
1.jsr方式实现权限控制：@RolesAllowed("ROLE_ADMIN")
2.spring-security框架自带：@Secured("ROLE_ADMIN")
3.spring-security框架自带的表达式实现：@PreAuthorize("hasRole('ROLE_ADMIN')")

* */
@Secured("ROLE_ADMIN")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("findAllUser")
    public String findAllUser(Model model, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize) {
        PageInfo<SysUser> sysUserPageInfo = userService.findAllUser(pageNum, pageSize);
        model.addAttribute("sysUserPageInfo", sysUserPageInfo);
        return "user/userList";
    }

    @RequestMapping("addUser")
    public String addUser() {
        return "user/userAdd";
    }

    @RequestMapping("userAddInfo")
    public String userAddInfo(Model model, SysUser sysUser) {
        userService.userAddInfo(sysUser);
        return "redirect:/User/findAllUser";

    }

    @RequestMapping("userDetail")
    public String userDetail(Model model, Integer userId) {
        SysUser sysUserInfo = userService.userDetail(userId);
        model.addAttribute("sysUserInfo", sysUserInfo);
        return "user/userDetail";
    }

    @RequestMapping("settingrUserRole")
    public String settingrUserRole(Model model, Integer userId) {
        //得到所有用户与对应的角色的封装信息
        SysUser userById = userService.findUserById(userId);
        //得到所有用户对应的角色
        List<SysRole> userByIdSysRoleList = userById.getSysRoleList();
        System.out.println(userByIdSysRoleList);
        if (userByIdSysRoleList != null && userByIdSysRoleList.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (SysRole sysRole : userByIdSysRoleList) {
                sb.append(sysRole.getRoleName() + ",");
            }
            System.out.println(sb.toString());
            model.addAttribute("sysRoleNameStr",sb.toString());
        }
        //注入角色的service,调用查询全部角色的方法
        List<SysRole> sysRoleList = roleService.fingAllRole();

        model.addAttribute("userById", userById);

        model.addAttribute("sysRoleList", sysRoleList);

        return "user/managerUserRole";
    }
    @RequestMapping("updateUserRole")
    public String updateUserRole(Integer userId,Integer[] ids){
        userService.updateUserRole(userId,ids);
        return "redirect:/User/findAllUser";
    }
    /*测试一个方法*/
}
