package com.liu.springbootdemo.controller;

import com.liu.springbootdemo.pojo.User;
import com.liu.springbootdemo.service.UserService;
import com.liu.springbootdemo.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller

public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String toindexPage(HttpServletRequest request, Model model){
        Cookie cookie = CookieUtil.get(request,"uid");
        if(null ==  cookie)
            return "login";
        else {
            User user = userService.getUserById(cookie.getValue());
            model.addAttribute("user",user);
            return "main";
        }
    }

    @RequestMapping("/{page}")
    public String toPage(@PathVariable String page){
        return page;
    }
}
