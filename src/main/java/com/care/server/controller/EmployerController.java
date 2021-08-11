package com.care.server.controller;

import java.security.Principal;
import java.util.List;

import com.care.server.dto.SeekerProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/numberof-applicants/{id}")
	public int getNumOfApplicants(@PathVariable("id") long id){
		System.out.println("In controller");
		return employerService.numberOfApplicants(id);
	}
	@GetMapping("/applicants-job/{jobId}")
	public List<SeekerProfileDto> getApplicantsProfile(@PathVariable("jobId") long id){
		return employerService.getApplicantProfile(id);
	}
	@GetMapping("/select-candidate/{jobId}/{candidateId}")
	public String selecteCandidate(@PathVariable("jobId") long jobId,@PathVariable("candidateId") long candidateId){
		System.out.println("jobId : "+jobId );
		System.out.println("candidateid : "+candidateId);
		return employerService.selectCandidate(jobId,candidateId);
	}
}
