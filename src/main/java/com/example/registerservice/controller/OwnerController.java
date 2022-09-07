package com.example.registerservice.controller;

import com.example.registerservice.dto.UpdateDTO;
import com.example.registerservice.entity.Owner;
import com.example.registerservice.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/registerService/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;
    @Operation(summary = "Save Owner API",description = "This api is used to save the owner details", tags = {"OwnerController"})
    @PostMapping(value = "/register")
    public String saveOwner(@RequestBody Owner owner){
        return ownerService.saveOwner(owner);
    }
    @Operation(summary = "Update User API",description = "This api is used to update the owner details", tags = {"OwnerController"})
    @PutMapping(value = "/update")
    public String updateUser(HttpServletRequest request , @RequestBody UpdateDTO updateOwnerDTO){
        return ownerService.updateOwner(request , updateOwnerDTO);
    }

    @Operation(summary = "Delete User API",description = "This api is used to delete the owner details", tags = {"OwnerController"})
    @DeleteMapping(value = "/delete/{ownerId}")
    public void deleteOwner(@PathVariable("ownerId") int id){
        ownerService.deleteOwner(id);
    }
}
