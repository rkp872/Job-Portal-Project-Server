package com.care.server.models.seeker;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.care.server.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SeekerContact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String email;
	private String alternateEmail;
	private String phone;
	private String homePhone;
	private String currentAddress;
	private String permanentAddress;
	private String facebookProfile;
	private String linkedInProfile;
	private String githubProfile;
	@OneToOne
	private User user;
}
