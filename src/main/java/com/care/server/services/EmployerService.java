package com.care.server.services;

import java.util.ArrayList;
import java.util.List;

import com.care.server.dto.SeekerProfileDto;
import com.care.server.models.AppliedJob;
import com.care.server.models.employer.SelectCandidate;
import com.care.server.models.seeker.*;
import com.care.server.repo.AppliedJobRepository;
import com.care.server.repo.employer.SelectCandidateRepository;
import com.care.server.repo.seeker.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.server.models.User;
import com.care.server.models.employer.NewJob;
import com.care.server.repo.UserRepository;
import com.care.server.repo.employer.NewJobRepository;

@Service
public class EmployerService {
	@Autowired
	private NewJobRepository newJobRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AppliedJobRepository appliedJobRepository;
	@Autowired
	private SeekerContactRepository seekerContactRepository;
	@Autowired
	private SeekerProfessionalRepository seekerProfessionalRepository;
	@Autowired
	private SeekerEducationRepository seekerEducationRepository;
	@Autowired
	private SeekerCertificationRepository seekerCertificationRepository;
	@Autowired
	public SeekerAchievementRepository seekerAchievementRepository;
	@Autowired
	public SeekerPersonalRepository seekerPersonalRepository;
	@Autowired
	public SeekerSummaryRepository seekerSummaryRepository;
	@Autowired
	private SelectCandidateRepository selectCandidateRepository;

	public boolean addNewJob(NewJob newJob, String email) {
		User user = userRepository.findByEmail(email);
		newJob.setPostedBy(user);
		newJobRepository.save(newJob);
		return true;
	}

	public List<NewJob> getOpenJobs(String email) {
		try {
			User user = userRepository.findByEmail(email);
			List<NewJob> openJobs = newJobRepository.findByPostedBy(user);
			return openJobs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public int numberOfApplicants(long jobId){
		NewJob newJob = newJobRepository.findById(jobId).get();
		int numberOfApplicants = appliedJobRepository.countByNewJob(newJob);
		return numberOfApplicants;
	}
	public List<SeekerProfileDto> getApplicantProfile(long jobId){
		NewJob job = newJobRepository.findById(jobId).get();
		List<AppliedJob> appliedJobs = appliedJobRepository.findByNewJob(job);
		List<User> applicantsList=new ArrayList<>();
		appliedJobs.forEach(appliedJob -> {
			applicantsList.add(appliedJob.getUser());
		});
		ArrayList<SeekerProfileDto> applicantsProfileList=new ArrayList<SeekerProfileDto>();
		List<SeekerAchievment> seekerAchievment=new ArrayList<>();
		List<SeekerCertification> seekerCertification=new ArrayList<>();
		SeekerContact seekerContact=new SeekerContact();
		List<SeekerEducation> seekerEducation=new ArrayList<>();
		SeekerPersonal seekerPersonal=new SeekerPersonal();
		List<SeekerProfessional> seekerProfessional=new ArrayList<>();
		SeekerSummary seekerSummary=new SeekerSummary();


		for (User user : applicantsList) {
			seekerAchievment = seekerAchievementRepository.findByUser(user);
			seekerCertification=seekerCertificationRepository.findByUser(user);
			seekerContact=seekerContactRepository.findByUser(user);
			seekerEducation=seekerEducationRepository.findByUser(user);
			seekerPersonal=seekerPersonalRepository.findByUser(user);
			seekerProfessional=seekerProfessionalRepository.findByUser(user);
			seekerSummary=seekerSummaryRepository.findByUser(user);
			//SeekerProfileDto seekerProfileDto=new SeekerProfileDto(seekerSummary,seekerAchievment,seekerCertification,seekerContact,seekerEducation,seekerPersonal,seekerProfessional);
			applicantsProfileList.add(new SeekerProfileDto(user,seekerSummary,seekerAchievment,seekerCertification,seekerContact,seekerEducation,seekerPersonal,seekerProfessional));
		}
		System.out.println(applicantsProfileList);
		return applicantsProfileList;
	}
	public String selectCandidate(long jobId,long userId){
		NewJob job=newJobRepository.findById(jobId).get();
		User candidate=userRepository.findById(userId).get();
		SelectCandidate dbOutput = selectCandidateRepository.findByNewJobAndUser(job, candidate);
		if(dbOutput!=null)
			return "This candidate already Seleted for this job";
		SelectCandidate selectCandidate=new SelectCandidate();
		selectCandidate.setNewJob(job);
		selectCandidate.setUser(candidate);
		selectCandidateRepository.save(selectCandidate);
		return "Candidate Selected For next round";
	}
}
