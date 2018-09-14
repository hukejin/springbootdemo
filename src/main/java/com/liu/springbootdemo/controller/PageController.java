package com.liu.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面之间的跳转
 */
@Controller
public class PageController {

    @RequestMapping("/course/{fragmentname}/{coursetype}")
    public String toPageHref(@PathVariable String fragmentname,@PathVariable String coursetype){
        return fragmentname + " :: #content " + "('" + coursetype + "')";
    }

    @RequestMapping("/{page}")
    public String toPage(@PathVariable String page){
        return page;
    }
}
