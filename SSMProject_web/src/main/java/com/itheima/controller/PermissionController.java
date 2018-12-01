package com.itheima.controller;

import com.itheima.domain.SysPermission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("Permission")
@Secured("ROLE_ADMIN")
public class PermissionController {
    @Autowired
   private PermissionService permissionService;
    @RequestMapping("findAll")
    public String findAll(Model model) {
        List<SysPermission> permissionList = permissionService.findAllPermission();
        model.addAttribute("permissionList", permissionList);
        return "permission/permissionList";
    }

    @RequestMapping("save")
    public String findAll(SysPermission peimission) {
        permissionService.save(peimission);
        return "redirect:findAll";
    }
    @RequestMapping("addPermission")
    public String addPermission(){
        return "permission/permissionAdd";
    }

}
