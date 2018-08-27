package com.liu.springbootdemo.service;

import com.liu.springbootdemo.pojo.User;
import java.util.List;


public interface UserService {

    String addUser(User user);
    String removeUser(User user);
    String update(User user);
    User getUserById(String id);
    List<User> getUsersByName(String name);
}
