package com.example.registerservice.controller;

import com.example.registerservice.entity.User;
import com.example.registerservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }
}
