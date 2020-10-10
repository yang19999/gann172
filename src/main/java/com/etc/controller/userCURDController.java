package com.etc.controller;

import com.etc.entity.User;
import com.etc.service.IUserInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class userCURDController {
    @Resource
    IUserInfoService userInfoService;

    @RequestMapping("/userlogin")
    public ModelAndView userlogin(String name, String password, HttpServletResponse response, HttpSession session) throws Exception{
        ModelAndView mv = new ModelAndView(new InternalResourceView("/queryUser"));
        if(name == null && password == null){//检证
            mv.setViewName("login");
            return mv;
        }
        User u = userInfoService.loginQuery(name,password);
        System.out.println("Controller-----------"+u);
        if(u != null){//登陆成功
            session.setAttribute("currentUser",u);
            System.out.println(u);
        }else{
            mv.setViewName("login");
            mv.addObject("error","用户名密码错误！");
        }
        return mv;
    }


    @RequestMapping("/queryUser2")
    public ModelAndView queryUser2(){
        List<User> users = userInfoService.listAllUserInfo(null);
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("userList",users);
        return mv;
    }
    @RequestMapping("/queryUser")
    @ResponseBody
    public ModelAndView queryUserByPage(@RequestParam(required = true,name = "pageNum",defaultValue = "1")Integer pageNum,
                                        @RequestParam(name = "pageSize",defaultValue = "5") Integer pageSize){
        PageInfo pageInfo = userInfoService.findPage(pageNum, pageSize);
        ModelAndView mv = new ModelAndView("userquery");
        mv.addObject("p",pageInfo);
        return mv;
    }

    @RequestMapping("/usertoadd")
    public ModelAndView toadd(){
        ModelAndView mv = new ModelAndView("useradd");
        return mv;
    }

    @RequestMapping("/userget/{uid}")
    public ModelAndView get(@PathVariable Integer uid){
        ModelAndView mv = new ModelAndView("userget");
        mv.addObject("c",userInfoService.listUserInfoByID(uid));
        return mv;
    }

    @RequestMapping("/useradd")
    public ModelAndView add(User user){
        userInfoService.addUserInfo(user);
        ModelAndView mv = new ModelAndView(new RedirectView("userget/"+user.getUid()));//重定向到另一个url影射
//        ModelAndView mv = new ModelAndView(new InternalResourceView("/queryUser"));
        mv.addObject("msg","新加成功");
        return mv;
    }

    @RequestMapping("/usermod")
    public ModelAndView mod(User user){
        ModelAndView mv = new ModelAndView(new InternalResourceView("userget/"+user.getUid()));//转发向到另一个url影射
        userInfoService.modUserInfoByID(user);
        mv.addObject("msg","修改成功");
        return mv;
    }

    @RequestMapping("/userdel/{uid}")
    public ModelAndView del(@PathVariable Integer uid){
        ModelAndView mv = new ModelAndView(new InternalResourceView("/queryUser"));//转发向到另一个url影射
        userInfoService.delUserInfoByID(uid);
        mv.addObject("msg","删除成功");
        return mv;
    }
}
