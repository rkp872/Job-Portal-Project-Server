package com.care.server.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.care.server.models.employer.NewJob;
import com.care.server.services.EmployerService;

@RestController
@RequestMapping("/employer")
public class EmployerController {
	@Autowired
	private EmployerService employerService;

	@PostMapping("/add-newjob")
	public String addNewJob(@RequestBody NewJob newJob, Principal principal) {
		System.out.println(newJob);
		employerService.addNewJob(newJob, principal.getName());
		return "Data reached to server";
	}

	@GetMapping("/get-openjobs")
	public List<NewJob> getOpenJobs(Principal principal) {
		return employerService.getOpenJobs(principal.getName());
	}
}
