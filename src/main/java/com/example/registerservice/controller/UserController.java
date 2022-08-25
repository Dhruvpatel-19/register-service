package com.example.registerservice.controller;

import com.example.registerservice.entity.User;
import com.example.registerservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/registerService/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public String saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }
}
