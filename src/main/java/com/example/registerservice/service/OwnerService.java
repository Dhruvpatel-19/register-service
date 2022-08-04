package com.example.registerservice.service;

import com.example.registerservice.entity.Owner;
import com.example.registerservice.repository.OwnerRepository;
import com.example.registerservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OwnerService{

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String saveOwner(Owner owner){

        boolean isOwnerExist = ownerRepository.existsByEmail(owner.getEmail());

        boolean isUserExist = userRepository.existsByEmail(owner.getEmail());

        if(!isOwnerExist && !isUserExist) {
            owner.setCreatedAt(LocalDateTime.now());

            String password = owner.getPassword();
            String encryptedPassword = bCryptPasswordEncoder.encode(password);
            owner.setPassword(encryptedPassword);

            ownerRepository.save(owner);

            return "Owner added successfuly";
        }
        else {
            return "Email already exists";
        }
    }
}
