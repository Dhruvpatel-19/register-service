package com.example.registerservice.controller;

import com.example.registerservice.entity.Owner;
import com.example.registerservice.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/registerService/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping(value = "/register")
    public String saveOwner(@RequestBody Owner owner){
        return ownerService.saveOwner(owner);
    }
}
