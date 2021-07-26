package com.care.server.controller;

import com.care.server.models.seeker.SeekerContact;
import com.care.server.models.seeker.SeekerEducation;
import com.care.server.models.seeker.SeekerProfessional;
import com.care.server.services.SeekerSeervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
    @PostMapping("/add-professional")
    public String saveProfessionalData(@RequestBody SeekerProfessional seekerProfessional,Principal principal){
        seekerSeervice.saveProfessionalData(seekerProfessional,principal.getName());
        return "Saved Successfully";
    }
    @GetMapping("/get-professional")
    public List<SeekerProfessional> getProfessionalData(Principal principal){
        return seekerSeervice.getProfessionalData(principal.getName());
    }
    @GetMapping("delete-professional/{id}")
    public String deleteProfessionalData(@PathVariable("id") long id,Principal principal){
        System.out.println("id : "+id);
        if(seekerSeervice.deleteProfssionalData(id,principal.getName())) return "deleted";
        else return "Invalid Request";
    }
    @PostMapping("/add-education")
    public String addEducationData(@RequestBody SeekerEducation seekerEducation,Principal principal){
        if(seekerSeervice.addEducationData(seekerEducation,principal.getName()))
            return "Added";
        else
            return "Server Error";

    }
    @GetMapping("/delete-education/{id}")
    public String deleteEducation(@PathVariable("id") long id,Principal principal){
        if(seekerSeervice.deleteEducationData(id,principal.getName())) return "Deleted";
        else return "Invalid Request";
    }
    @GetMapping("/get-education")
    public List<SeekerEducation> fetchEducationData(Principal principal){
        return seekerSeervice.getEducationData(principal.getName() );
    }
}
