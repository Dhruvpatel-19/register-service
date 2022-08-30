package com.example.registerservice.service;

import com.example.registerservice.dto.UpdateDTO;
import com.example.registerservice.entity.User;
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
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    public String saveUser(User user){

        boolean isOwnerExist = ownerRepository.existsByEmail(user.getEmail());

        boolean isUserExist = userRepository.existsByEmail(user.getEmail());

        if(!isOwnerExist && !isUserExist) {
            user.setCreatedAt(LocalDateTime.now());

            String password = user.getPassword();
            String encryptedPassword = bCryptPasswordEncoder.encode(password);
            user.setPassword(encryptedPassword);

            HttpEntity<User> userObj = new HttpEntity<>(user);
            //ResponseEntity<String> userRegisteredResponse = restTemplate.postForEntity("http://localhost:8084/callWelcomeService/user/register" , userObj , String.class);
            restTemplate.postForEntity("http://localhost:8084/callWelcomeService/user/register" , userObj , String.class);

            userRepository.save(user);

            return "User saved successfully";

        }
        else {
            return "Email already exist";
        }
    }

    public String updateUser(HttpServletRequest request , UpdateDTO updateUserDTO) throws Exception {
        String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String email = null;
        System.out.println("requestTokenHeader : "+requestTokenHeader);
        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")){
            jwtToken = requestTokenHeader.substring(7);
            try {
                email = jwtUtil.extractUsername(jwtToken);
            }catch (Exception e){
                throw new Exception("User not found");
            }
            System.out.println("Email : "+email);
            User user = userRepository.findByEmail(email);
            user.setFirstName(updateUserDTO.getFirstName());
            user.setLastName(updateUserDTO.getLastName());
            user.setMobileNumber(updateUserDTO.getMobileNumber());

            HttpEntity<User> userObj = new HttpEntity<>(user);
            restTemplate.exchange("http://localhost:8084/callWelcomeService/user/update/"+user.getUserId(), HttpMethod.PUT  , userObj , String.class);
            userRepository.save(user);

            return "User updated successfully";
        }
        return "Some error occured while updateProfile for User";
    }
}
