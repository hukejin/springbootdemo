package com.liu.springbootdemo.controller;

import com.liu.springbootdemo.entity.Course;
import com.liu.springbootdemo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @RequestMapping("/{type}")
    public String getCoursesByType(@PathVariable String type, Model model){
        model.addAttribute("type",type);
        return "list";
    }

    @RequestMapping("/courselist/{type}")
    @ResponseBody
    public Map<String,Object> getCourseListByType(@PathVariable String type){
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",10);
        map.put("data",courseRepository.getAllByCoursetypeEqualsOrderByCourselevelAsc(type));
        return map;
    }
}
