package com.example.registerservice.service;

import com.example.registerservice.entity.Owner;
import com.example.registerservice.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OwnerService{

    @Autowired
    private OwnerRepository ownerRepository;

    public Owner saveOwner(Owner owner){
        owner.setCreatedAt(LocalDateTime.now());
        return ownerRepository.save(owner);
    }
}
