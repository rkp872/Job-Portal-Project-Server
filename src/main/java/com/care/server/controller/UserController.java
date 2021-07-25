package com.care.server.controller;

import com.care.server.config.JwtUtil;
import com.care.server.config.MyUserDetailsService;
import com.care.server.dto.AuthenticationResponse;
import com.care.server.dto.LoginUser;
import com.care.server.dto.RegisterUser;
import com.care.server.helper.Message;
import com.care.server.models.User;
import com.care.server.repo.UserRepository;
import com.care.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
   public ResponseEntity<?> registerUser(@RequestBody RegisterUser regUser){
        System.out.println(regUser);


        if (userService.registerUser(regUser)) {
            return ResponseEntity.status(HttpStatus.OK).body(new Message("Registered Successfully", "success"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new Message("Registration Failed Try Again !!", "danger"));
        }
   }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginUser authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("Invalid Credentials", "danger"));
        }

        UserDetails userdetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        String token = jwtUtil.generateToken(userdetails);
        User user = userRepository.findByEmail(authenticationRequest.getEmail());
        user.setPassword("***Not Available***");
        if (user.isVerified()) {
            return ResponseEntity.ok(new AuthenticationResponse(token, user));
        } else {
            return ResponseEntity.status(HttpStatus.LOCKED)
                    .body(new Message("Your account is not verified yet", "danger"));
        }

    }

}
