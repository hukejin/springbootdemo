package com.liu.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.liu.springbootdemo.dao.UserMapper;
import com.liu.springbootdemo.pojo.User;
import com.liu.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
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
}
