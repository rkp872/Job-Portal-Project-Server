package com.care.server.controller;

import com.care.server.models.employer.NewJob;
import com.care.server.services.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/employer")
public class EmployerController {
    @Autowired
    private EmployerService employerService;
    @PostMapping("/add-newjob")
    public String addNewJob(@RequestBody NewJob newJob, Principal principal){
        System.out.println(newJob);
        employerService.addNewJob(newJob,principal.getName());
        return "Data reached to server";
    }
}
