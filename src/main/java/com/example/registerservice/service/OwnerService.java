package com.example.registerservice.service;

import com.example.registerservice.dto.UpdateDTO;
import com.example.registerservice.entity.Owner;
import com.example.registerservice.jwt.JwtUtil;
import com.example.registerservice.repository.OwnerRepository;
import com.example.registerservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class OwnerService{

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    public String saveOwner(Owner owner){

        boolean isOwnerExist = ownerRepository.existsByEmail(owner.getEmail());

        boolean isUserExist = userRepository.existsByEmail(owner.getEmail());

        if(!isOwnerExist && !isUserExist) {
            owner.setCreatedAt(LocalDateTime.now());

            String password = owner.getPassword();
            String encryptedPassword = bCryptPasswordEncoder.encode(password);
            owner.setPassword(encryptedPassword);

            HttpEntity<Owner> ownerObj = new HttpEntity<>(owner);
            //ResponseEntity<String> ownerRegisteredResponse =  restTemplate.postForEntity("http://localhost:8084/callWelcomeService/owner/register" , ownerObj , String.class);
            restTemplate.postForEntity("http://localhost:8085/callPostService/owner/register" , ownerObj , String.class);

            ownerRepository.save(owner);

            return "Owner added successfuly";
        }
        else {
            return "Email already exists";
        }
    }

    public String updateOwner(HttpServletRequest request, UpdateDTO updateDTO) throws Exception {
        String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String email = null;

        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")){
            jwtToken = requestTokenHeader.substring(7);
            try {
                email = jwtUtil.extractUsername(jwtToken);
            }catch (Exception e){
                throw new Exception("User not found");
            }
            System.out.println("Email : "+email);
            Owner owner = ownerRepository.findByEmail(email);
            owner.setFirstName(updateDTO.getFirstName());
            owner.setLastName(updateDTO.getLastName());
            owner.setMobileNumber(updateDTO.getMobileNumber());

            HttpEntity<Owner> ownerObj = new HttpEntity<>(owner);
            restTemplate.exchange("http://localhost:8085/callPostService/owner/update/"+owner.getOwnerId() , HttpMethod.PUT , ownerObj , String.class);
            ownerRepository.save(owner);

            return "Owner updated successfully";
        }
        return "Some error occured while updateProfile for Owner";
    }
}
