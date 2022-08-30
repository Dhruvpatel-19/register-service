package com.example.registerservice.controller;

import com.example.registerservice.dto.UpdateDTO;
import com.example.registerservice.entity.User;
import com.example.registerservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/registerService/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public String saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping(value = "/update")
    public String updateUser(HttpServletRequest request , @RequestBody UpdateDTO updateUserDTO) throws Exception {
        return userService.updateUser(request , updateUserDTO);
    }
}
