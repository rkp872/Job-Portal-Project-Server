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
public class SeekerProfessional {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String jobTitle;
    private String duration;
    private String role;
    private String location;
    private String description;
    @OneToOne
    private User user;
}
