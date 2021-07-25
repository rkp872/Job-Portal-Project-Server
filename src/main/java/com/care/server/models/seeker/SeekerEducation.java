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
public class SeekerEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String degree;
    private String specialization;
    private String university;
    private String year;
    private String percentage;
    @OneToOne
    private User user;
}
