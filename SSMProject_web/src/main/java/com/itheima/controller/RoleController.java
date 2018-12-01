package com.itheima.controller;

import com.itheima.domain.SysPermission;
import com.itheima.domain.SysRole;
import com.itheima.service.PermissionService;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("Role")
@Secured("ROLE_ADMIN")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("findAllRole")
    public String findAllRole(Model model) {
        List<SysRole> sysRoleList = roleService.fingAllRole();
        model.addAttribute("sysRoleList", sysRoleList);
        return "role/roleList";
    }

    @RequestMapping("addRole")
    public String addRole() {
        return "role/roleAdd";
    }

    @RequestMapping("roleAddInfo")
    public String roleAddInfo(Model model, SysRole sysRole) {
        roleService.roleAddInfo(sysRole);
        return "redirect:/Role/findAllRole";
    }

    @RequestMapping("managerRolePermission")
    public String managerRolePermission(Model model, Integer roleId) {

        SysRole role = roleService.findRoleById(roleId);
        model.addAttribute("role", role);
        System.out.println(role.getRoleName());
       List<SysPermission> sysPermissionList=role.getPermissionList();

        //角色的权限集合
        System.out.println(sysPermissionList+"===============================");
        //遍历角色的权限信息柱状字符串返回页面判断
        if (null != sysPermissionList && sysPermissionList.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (SysPermission permission : sysPermissionList) {
                sb.append(permission.getPermissionName() + ",");
            }
            model.addAttribute("permissionsStr", sb.toString());
        }
        //返回所有的数据库权限信息
       List<SysPermission> permissionList = permissionService.findAllPermission();
        model.addAttribute("permissionList", permissionList);
        return "role/managerRolePremission";
    }

    @RequestMapping("updateRolePermission")
    public String updateRolePermission(Integer roleId, Integer[] ids) {
        roleService.updateRolePermission(roleId, ids);
        return "redirect:/Role/findAllRole";

    }
}
