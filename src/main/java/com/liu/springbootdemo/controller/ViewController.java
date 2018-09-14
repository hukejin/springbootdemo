package com.liu.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ViewController {

    @RequestMapping("/")
    public String toLogin(){
        return "login";
    }
}
