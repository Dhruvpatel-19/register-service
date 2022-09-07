package com.example.registerservice.controller;

import com.example.registerservice.dto.UpdateDTO;
import com.example.registerservice.entity.User;
import com.example.registerservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/registerService/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Operation(summary = "Save user API",description = "This api is used to save the user", tags = {"UserController"})
    @PostMapping(value = "/register")
    public String saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }
    @Operation(summary = "Update User API",description = "This api is used to update user details", tags = {"UserController"})
    @PutMapping(value = "/update")
    public String updateUser(HttpServletRequest request , @RequestBody UpdateDTO updateUserDTO) throws Exception {
        return userService.updateUser(request , updateUserDTO);
    }

    @Operation(summary = "Delete User API",description = "This api is used to delete the user details", tags = {"UserController"})
    @DeleteMapping(value = "/delete/{userId}")
    public void deleteOwner(@PathVariable("userId") int id){
        userService.deleteUser(id);
    }
}
