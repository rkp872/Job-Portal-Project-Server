package com.care.server.services;

import com.care.server.models.seeker.SeekerEducation;
import com.care.server.models.seeker.SeekerProfessional;
import com.care.server.repo.seeker.SeekerEducationRepository;
import com.care.server.repo.seeker.SeekerProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.server.models.User;
import com.care.server.models.seeker.SeekerContact;
import com.care.server.repo.UserRepository;
import com.care.server.repo.seeker.SeekerContactRepository;

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
}
