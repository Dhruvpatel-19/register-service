package com.example.registerservice.service;

import com.example.registerservice.entity.Owner;
import com.example.registerservice.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OwnerService{

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Owner saveOwner(Owner owner){
        owner.setCreatedAt(LocalDateTime.now());

        String password = owner.getPassword();
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        owner.setPassword(encryptedPassword);

        return ownerRepository.save(owner);
    }
}
