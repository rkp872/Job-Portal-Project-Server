package com.care.server.controller;

import com.care.server.models.seeker.SeekerContact;
import com.care.server.services.SeekerSeervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/seeker")
public class SeekerController {
    @Autowired
    private SeekerSeervice seekerSeervice;

    @GetMapping("/get-contact")
    public SeekerContact getContact(Principal principal){
        return seekerSeervice.getContact(principal.getName());
    }

    @PostMapping("/update-contact")
    public String updateContact(@RequestBody SeekerContact seekerContact, Principal principal){
        boolean result = seekerSeervice.updateContact(seekerContact, principal.getName());
        return "Updated SUccessfully";
    }
}
