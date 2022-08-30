package com.example.registerservice.controller;

import com.example.registerservice.dto.UpdateDTO;
import com.example.registerservice.entity.Owner;
import com.example.registerservice.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/registerService/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping(value = "/register")
    public String saveOwner(@RequestBody Owner owner){
        return ownerService.saveOwner(owner);
    }

    @PutMapping(value = "/update")
    public String updateUser(HttpServletRequest request , @RequestBody UpdateDTO updateOwnerDTO) throws Exception {
        return ownerService.updateOwner(request , updateOwnerDTO);
    }
}
