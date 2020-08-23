package com.yoa.controller;

import com.yoa.base.model.ReturnDTO;
import com.yoa.shiro.ShiroUser;
import com.yoa.util.Global;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {
    ReturnDTO result=new ReturnDTO();
    @Autowired
    DocumentController documentController;

    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("login");
        return mv;
    }
    @PostMapping("/doLogin")
    public ModelAndView login(String userName,String pwd) {
        ModelAndView mv=new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, pwd);
        String error="";
        try {
            // 从自定义Realm获取安全数据进行验证
            subject.login(token);
            ShiroUser user=(ShiroUser)subject.getPrincipal();
            subject.getSession().setAttribute("user",user);
            mv.setViewName("user-list");
            return documentController.activeToMe();
        } catch (DisabledAccountException e) {
            error ="用户已被禁用,请登录其他用户";
            mv.addObject("error",error);
        } catch (UnknownAccountException e) {
            error ="用户不存在,请检查后重试!";
            mv.addObject("error",error);
        } catch (IncorrectCredentialsException e) {
            error ="密码错误";
            mv.addObject("error",error);
        } catch (Exception e){
            error=e.getMessage();
            mv.addObject("error",error);
        }
        mv.setViewName("login");
        return mv;
    }
    //@RequiresPermissions("3")
    @RequestMapping("/te")
    public ReturnDTO test() {
        Subject subject = SecurityUtils.getSubject();
        subject.getPrincipal();
        //subject.getSession().setTimeout(1000);\
        return result.success(Global.getUser());
    }

    @RequestMapping("/logout")
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
    @RequestMapping("/unAuthorized")
    public ReturnDTO noPermissions() {
        return result.fail(505,"No Permissions");
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

}
