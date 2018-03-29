package com.socialClub.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by peng.tian on 2018/3/28
 */
@RestController
public class LoginController {


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public boolean login(@RequestParam String email, @RequestParam String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(email, password);
        subject.login(token);
        return subject.hasRole("admin") || subject.isAuthenticated();
    }


    @RequestMapping("/get")
    @RequiresPermissions("create")
    public String getInfo(){
        return "success!";
    }


}
