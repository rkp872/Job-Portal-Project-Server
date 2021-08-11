package com.care.server.services;

import com.care.server.models.AppliedJob;
import com.care.server.models.employer.NewJob;
import com.care.server.models.employer.SelectCandidate;
import com.care.server.models.seeker.*;
import com.care.server.repo.AppliedJobRepository;
import com.care.server.repo.employer.NewJobRepository;
import com.care.server.repo.employer.SelectCandidateRepository;
import com.care.server.repo.seeker.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.server.models.User;
import com.care.server.repo.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeekerSeervice {
	@Autowired
	private SeekerContactRepository seekerContactRepository;
	@Autowired
	private UserRepository userRepository;
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
	private NewJobRepository newJobRepository;
	@Autowired
	private AppliedJobRepository appliedJobRepository;
	@Autowired
	private SelectCandidateRepository selectCandidateRepository;

	public SeekerContact getContact(String email) {
		System.out.println("Email : " + email);
		User user = userRepository.findByEmail(email);
		System.out.println("User : " + user);
		SeekerContact contactFromDb = seekerContactRepository.findByUser(user);
		if (contactFromDb == null) {
			return null;
		}
		contactFromDb.getUser().setPassword("**Not Available**");
		return contactFromDb;
	}

	public boolean updateContact(SeekerContact seekerContact, String email) {
		try {
			User user = userRepository.findByEmail(email);
			SeekerContact findByUser = seekerContactRepository.findByUser(user);
			if (findByUser == null) {
				seekerContact.setUser(user);
				SeekerContact savedContact = seekerContactRepository.save(seekerContact);
			} else {
				seekerContact.setId(findByUser.getId());
				seekerContactRepository.save(seekerContact);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean saveProfessionalData(SeekerProfessional seekerProfessional,String email){
		try {
			User user=userRepository.findByEmail(email);
			seekerProfessional.setUser(user);
			seekerProfessionalRepository.save(seekerProfessional);
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public List<SeekerProfessional> getProfessionalData(String email){
		User user=userRepository.findByEmail(email);
		List<SeekerProfessional> seekerProfessionals = seekerProfessionalRepository.findByUser(user);
		seekerProfessionals.forEach(seekerProfessional -> {
			seekerProfessional.getUser().setPassword(("**Not Available**"));
		});
		System.out.println(seekerProfessionals);
		return  seekerProfessionals;
	}
	public boolean deleteProfssionalData(long id,String email){
		SeekerProfessional professionalData = seekerProfessionalRepository.findById(id).get();
		User user=userRepository.findByEmail(email);
		if(user.getId()==professionalData.getUser().getId()){
			seekerProfessionalRepository.delete(professionalData);
			return true;
		}
		else
			return false;
	}
	public boolean addEducationData(SeekerEducation seekerEducation, String email){
		try {
			User user=userRepository.findByEmail(email);
			seekerEducation.setUser(user);
			seekerEducationRepository.save(seekerEducation);
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}

	}
	public boolean deleteEducationData(long id,String email){
		SeekerEducation seekerEducation = seekerEducationRepository.findById(id).get();
		User user=userRepository.findByEmail(email);
		if(user.getId()==seekerEducation.getUser().getId()){
			seekerEducationRepository.delete(seekerEducation);
			return  true;
		}
		else return false;
	}
	public List<SeekerEducation> getEducationData(String email){
		User user=userRepository.findByEmail(email);
		List<SeekerEducation> seekerEducations= seekerEducationRepository.findByUser(user);
		seekerEducations.forEach(seekerEducation -> {
			seekerEducation.getUser().setPassword("**Not Available**");
		});
		return  seekerEducations;
	}
	public List<SeekerCertification> fetchCertificationData(String email){
		User user=userRepository.findByEmail(email);
		List<SeekerCertification> seekerCertifications = seekerCertificationRepository.findByUser(user);
		seekerCertifications.forEach(seekerCertification -> {
			seekerCertification.getUser().setPassword("**Not Available**");
		});
		return seekerCertifications;
	}
	public boolean addCertification(SeekerCertification seekerCertification, String email){
		User user=userRepository.findByEmail(email);
		seekerCertification.setUser(user);
		seekerCertificationRepository.save(seekerCertification);
		return true;
	}
	public boolean deleteCertification(long id,String email){
		User user=userRepository.findByEmail(email);
		SeekerCertification seekerCertification = seekerCertificationRepository.findById(id).get();
		if(user.getId()==seekerCertification.getUser().getId()){
			seekerCertificationRepository.delete(seekerCertification);
			return true;
		}
		else
			return false;
	}
	public List<SeekerAchievment> fetchAchievements(String email){
		User user=userRepository.findByEmail(email);
		List<SeekerAchievment> seekerAchievments = seekerAchievementRepository.findByUser(user);
		seekerAchievments.forEach(seekerAchievment -> {
			seekerAchievment.getUser().setPassword("**Not AVailable**");
		});
		return seekerAchievments;
	}
	public boolean addAchievement(SeekerAchievment seekerAchievment,String email){
		User user=userRepository.findByEmail(email);
		seekerAchievment.setUser(user);
		seekerAchievementRepository.save(seekerAchievment);
		return  true;
	}
	public boolean deleteAchievement(long id,String email){
		User user=userRepository.findByEmail(email);
		SeekerAchievment seekerAchievment = seekerAchievementRepository.findById(id).get();
		if(user.getId()==seekerAchievment.getUser().getId()){
			seekerAchievementRepository.delete(seekerAchievment);
			return true;
		}
		else
			return false;
	}
	public SeekerPersonal fetchPersonalData(String email){
		User user=userRepository.findByEmail(email);
		SeekerPersonal seekerPersonal = seekerPersonalRepository.findByUser(user);
		seekerPersonal.getUser().setPassword("**Not Available**");
		return seekerPersonal;
	}
	public boolean updatePersonalData(SeekerPersonal seekerPersonal,String email){
		User user=userRepository.findByEmail(email);

		SeekerPersonal seekerPersonalFromDb = seekerPersonalRepository.findByUser(user);
		if(seekerPersonalFromDb!=null)
			seekerPersonal.setId(seekerPersonalFromDb.getId());
		else
			seekerPersonal.setUser(user);
		seekerPersonalRepository.save(seekerPersonal);
		return true;
	}
	public SeekerSummary fetchSummary(String email){
		User user=userRepository.findByEmail(email);
		SeekerSummary seekerSummary = seekerSummaryRepository.findByUser(user);
		seekerSummary.getUser().setPassword("**Not Available**");
		return seekerSummary;
	}
	public boolean updateSummary(SeekerSummary seekerSummary,String email){
		User user=userRepository.findByEmail(email);
		SeekerSummary seekerSummaryFromDb = seekerSummaryRepository.findByUser(user);
		if(seekerSummaryFromDb!=null)
			seekerSummary.setId(seekerSummaryFromDb.getId());
		else
			seekerSummary.setUser(user);
		seekerSummaryRepository.save(seekerSummary);
		return true;
	}
	public List<NewJob> getRecommendedJobs(){
		return newJobRepository.findAll();
	}
	public boolean applyToJob(long jobId, String email){
		User user=userRepository.findByEmail(email);
		NewJob job=newJobRepository.findById(jobId).get();
		if(appliedJobRepository.findByNewJobAndUser(job,user)!=null)
			return false;
		AppliedJob appliedJob=new AppliedJob();
		appliedJob.setNewJob(job);
		appliedJob.setUser(user);
		appliedJobRepository.save(appliedJob);
		return true;
	}
	public int numberOfApplicants(long jobId){
		NewJob newJob = newJobRepository.findById(jobId).get();
		int numberOfApplicants = appliedJobRepository.countByNewJob(newJob);
		return numberOfApplicants;
	}
	public List<NewJob> getSelectedJob(String email){
		User user=userRepository.findByEmail(email);
		List<SelectCandidate> selectedJobs=selectCandidateRepository.findByUser(user);
		if(selectedJobs==null){
			return null;
		}
		ArrayList<NewJob> newJobs=new ArrayList<NewJob>();
		selectedJobs.forEach(selectCandidate -> {
			newJobs.add(selectCandidate.getNewJob());
		});
		return newJobs;
	}
}
