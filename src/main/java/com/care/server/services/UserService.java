package com.care.server.services;

import com.care.server.dto.RegisterUser;
import com.care.server.models.User;
import com.care.server.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    public Boolean registerUser(RegisterUser regUser){
        try {
            User user=new User();
            user.setName(regUser.getName());
            user.setEmail(regUser.getEmail());
            user.setRole(regUser.getRole());
            user.setPassword(bcryptEncoder.encode(regUser.getPassword()));
            user.setVerified(true);

            userRepository.save(user);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
