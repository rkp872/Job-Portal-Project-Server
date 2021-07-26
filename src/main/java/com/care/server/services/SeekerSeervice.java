package com.care.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.server.models.User;
import com.care.server.models.seeker.SeekerContact;
import com.care.server.repo.UserRepository;
import com.care.server.repo.seeker.SeekerContactRepository;

@Service
public class SeekerSeervice {
	@Autowired
	private SeekerContactRepository seekerContactRepository;
	@Autowired
	private UserRepository userRepository;

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

}
