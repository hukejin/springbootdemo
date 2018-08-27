package com.liu.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DemoController {

    @RequestMapping("/liu/{name}")
    @ResponseBody
    private String sayHello(@PathVariable("name") String name){
        return "hello "+name;
    }

    @RequestMapping("/kill")
    private String showUser(){
        return "good";
    }
}
