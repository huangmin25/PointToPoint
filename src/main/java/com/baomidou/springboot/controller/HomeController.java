package com.baomidou.springboot.controller;

import com.baomidou.springboot.entity.User;
import com.baomidou.springboot.service.IUserService;
import com.baomidou.springboot.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private IUserService userService;


    @RequestMapping("/index")
    public String root(){
        System.err.println("进入了index页面了。。。。。。。。。");
        User userEntity = (User) SecurityUtils.getSubject().getPrincipal();
        System.out.println("当前用户为："+userEntity.getUsername());
        return "/index";
    }

    @RequestMapping("/dologin")
    public String dologin(){
        System.err.println("进入了dologin页面了。。。。。。。。。");
        return "/dologin";
    }



    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String ,String> map){
        System.out.println("user login .....");
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        User userEntity = (User) SecurityUtils.getSubject().getPrincipal();
        if(null!=userEntity){
            System.out.println("当前用户为："+userEntity.getUsername());
        }


        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "redirect:index";
        }


        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                "admin",
                MD5Util.MD5Encoder("123456"));
        //进行验证，这里可以捕获异常，然后返回对应信息
        usernamePasswordToken.setRememberMe(true);
        subject.login(usernamePasswordToken);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "unknownAccount";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                msg = "incorrectPassword";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        //认证成功由shiro框架自行处理
        return "login";
    }


    //访问此连接时会触发MyShiroRealm中的权限分配方法
    @RequestMapping("/permission")
    @RequiresPermissions("student:test")
    public void test(){
        System.out.println("permission  test");
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        // 注销登录
        subject.logout();
        subject.getSession().removeAttribute("userSession");
        System.out.println("进入了logout了");
        return "redirect:dologin";
    }
}
