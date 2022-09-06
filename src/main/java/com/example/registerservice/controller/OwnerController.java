package com.example.registerservice.controller;

import com.example.registerservice.dto.UpdateDTO;
import com.example.registerservice.entity.Owner;
import com.example.registerservice.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/registerService/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;
    @Operation(summary = "Save Owner API",description = "This api is used to save the owner details", tags = {"OwnerController"})
    @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Owner saved successfully"),
      @ApiResponse(responseCode = "400",description = "Bad Request"),
      @ApiResponse(responseCode = "404",description = "User not found"),
      @ApiResponse(responseCode = "403",description = "Forbidden")
    })
    @PostMapping(value = "/register")
    public String saveOwner(@RequestBody Owner owner){
        return ownerService.saveOwner(owner);
    }
    @Operation(summary = "Update User API",description = "This api is used to update the user details", tags = {"OwnerController"})
    @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User updated successfully"),
      @ApiResponse(responseCode = "400",description = "Bad Request"),
      @ApiResponse(responseCode = "404",description = "User not found"),
      @ApiResponse(responseCode = "403",description = "Forbidden")
    })
    @PutMapping(value = "/update")
    public String updateUser(HttpServletRequest request , @RequestBody UpdateDTO updateOwnerDTO) throws Exception {
        return ownerService.updateOwner(request , updateOwnerDTO);
    }
}
