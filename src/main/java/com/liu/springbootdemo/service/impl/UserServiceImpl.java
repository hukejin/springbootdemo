package com.liu.springbootdemo.service.impl;

import com.liu.springbootdemo.dao.UserMapper;
import com.liu.springbootdemo.pojo.User;
import com.liu.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public String addUser(User user) {
        return userMapper.insert(user)+"";
    }

    @Override
    public String removeUser(User user) {
        return userMapper.deleteByPrimaryKey(user.getId())+"";
    }

    @Override
    public String update(User user) {
        return userMapper.updateByPrimaryKey(user)+"";
    }

    @Override
    public User getUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userMapper.selectByName(name);
    }
}
