package com.example.registerservice.controller;

import com.example.registerservice.dto.UpdateDTO;
import com.example.registerservice.entity.User;
import com.example.registerservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/registerService/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Operation(summary = "Save user API",description = "This api is used to save the user", tags = {"UserController"})
    @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User saved successfully"),
      @ApiResponse(responseCode = "400",description = "Bad Request"),
      @ApiResponse(responseCode = "404",description = "User not found"),
      @ApiResponse(responseCode = "403",description = "Forbidden")
    })
    @PostMapping(value = "/register")
    public String saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }
    @Operation(summary = "Update User API",description = "This api is used to update user details", tags = {"UserController"})
    @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User updated successfully"),
      @ApiResponse(responseCode = "400",description = "Bad Request"),
      @ApiResponse(responseCode = "404",description = "User not found"),
      @ApiResponse(responseCode = "403",description = "Forbidden")
    })
    @PutMapping(value = "/update")
    public String updateUser(HttpServletRequest request , @RequestBody UpdateDTO updateUserDTO) throws Exception {
        return userService.updateUser(request , updateUserDTO);
    }
}
