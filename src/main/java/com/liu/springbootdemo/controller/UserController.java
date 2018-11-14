package com.liu.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.liu.springbootdemo.aop.LoginCheck;
import com.liu.springbootdemo.dao.UserMapper;
import com.liu.springbootdemo.pojo.User;
import com.liu.springbootdemo.service.UserService;
import com.liu.springbootdemo.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/add",method= POST)
    @ResponseBody
    public String insertUser(User user){
        user.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        user.setCreatetime(System.currentTimeMillis());
        String result = userService.addUser(user);
        return "ok:"+result;
    }

    @RequestMapping(value="/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public String removeUser(@PathVariable("id") String id){
        User user = new User();
        user.setId(id);
        String result = userService.removeUser(user);
        return "ok:"+result;
    }

    @RequestMapping(value = "/update",method= POST)
    @ResponseBody
    public String updateUser(User user){
        String result = userService.update(user);
        return "ok:"+result;
    }

    @RequestMapping(value = "/getitem/{id}",method= GET)
    @ResponseBody
    public String getUser(@PathVariable("id") String id){
        User user = userService.getUserById(id);
        String result = JSON.toJSONString(user);
        return result;
    }

    @RequestMapping(value = "/login")
    public ModelAndView toLogin(User user, ModelAndView model, HttpServletResponse response){
        List<User> users = userService.getUsersByName(user.getName());

        if(users==null || users.size() == 0){
            model.addObject("error","用户不存在");
            model.setViewName("login");
            return model;
        }
        User user1 = users.get(0);
        if(!user1.getPasswd().equals(user.getPasswd())){
            model.addObject("error","密码错误");
            model.setViewName("login");
            return model;
        }

        CookieUtil.set(response,"uid",user1.getId(),CookieUtil.EXPIRE);

        model.addObject("user",user1);
        model.setViewName("main");
        return model;
    }

    @RequestMapping("/{dir}/{page}")
    public String toUserConfig(@PathVariable String dir,@PathVariable String page){
        return dir + "/" + page;
    }
}
