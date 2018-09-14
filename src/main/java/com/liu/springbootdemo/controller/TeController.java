package com.liu.springbootdemo.controller;

import com.liu.springbootdemo.entity.Te;
import com.liu.springbootdemo.repository.TeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/te")
public class TeController {

    @Autowired
    private TeRepository teRepository;

    @RequestMapping("/add")
    public String addItem(){
        try{
            Te te = new Te();
            te.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
            te.setName(""+System.currentTimeMillis());
            teRepository.save(te);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }


        return "success";
    }

    @GetMapping("/getitem/{name}")
    public List<Te> getItem(@PathVariable String name){
        try {
            return teRepository.getAllByName(name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/getitem/like/{name}")
    public List<Te> getItemLike(@PathVariable String name){
        try {
//            return teRepository.getDistinctByNameIsLike("%"+name+"%");
            return teRepository.getDistinctByNameIsLike(name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/removeitem/{id}")
    public String removeItem(@PathVariable String id){
        try {
            teRepository.deleteById(id);
            return "delete success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "delete error";
    }

    @RequestMapping("/updateitem/{id}")
    public String updateItem(@PathVariable String id){
        try {
            Te te = teRepository.findById(id).get();
            te.setName("我的名字被改了，原名为"+te.getName());
            teRepository.save(te);
            return "update success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "update error";
    }
}
