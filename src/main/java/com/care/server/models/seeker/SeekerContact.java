package com.care.server.models.seeker;

import com.care.server.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String facebbokProfile;
    private String linkedInProfile;
    private String githubProfile;
    @OneToOne
    private User user;
}
