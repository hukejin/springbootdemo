package com.liu.springbootdemo.controller;

import com.liu.springbootdemo.entity.Course;
import com.liu.springbootdemo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @RequestMapping("/additem")
    public String addItem(Course course, RedirectAttributes attributes){
        //检测补全信息
        course.setId(UUID.randomUUID().toString().replaceAll("-",""));
        course.setCreatetime(System.currentTimeMillis());
        course.setUpdatetime(System.currentTimeMillis());
        if(!StringUtils.isEmpty(course.getPagecode())){
            int pcode = Integer.parseInt(course.getPagecode());
            if(pcode < 10)
                course.setPagecode("0" + pcode);
        }
        if(!StringUtils.isEmpty(course.getPageitemcode())){
            int pcode = Integer.parseInt(course.getPageitemcode());
            if(pcode < 10)
                course.setPageitemcode("0" + pcode);
        }
        String grade = course.getPcoursecode().substring(0,2);
        if(grade.startsWith("0"))
            course.setCourselevel(grade.substring(1));
        else
            course.setCourselevel(grade);
        course.setCoursecode(course.getPcoursecode()+ course.getCoursetype() + course.getPagecode() + course.getPageitemcode());
        courseRepository.save(course);
        return "/coursemanage";
    }

    @RequestMapping("/list/{pcoursecode}/{coursetype}")
    @ResponseBody
    public Map<String,Object> showList(@PathVariable String pcoursecode,@PathVariable String coursetype){
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",10);
        map.put("data",courseRepository.getALLByPcoursecodeAndCoursetypeOrderByCourselevelAsc(pcoursecode,coursetype));
        return map;
    }


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

    @RequestMapping("/coursecodes/{cousecode}")
    @ResponseBody
    public Map<String,Object> getCourseListByCoursecode(@PathVariable String cousecode,Model model){
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",10);
        map.put("data",courseRepository.getAllByCoursecodeEquals(cousecode));
        model.addAttribute(map);
        return map;
    }
}
