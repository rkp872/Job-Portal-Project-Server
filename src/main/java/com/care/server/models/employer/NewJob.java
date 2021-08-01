package com.care.server.models.employer;


import com.care.server.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NewJob {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String jobTitle;
    private String location;
    private  int numOfOpening;
    @ElementCollection
    private List<String> skills;
    @ElementCollection
    private List<String> responsibilities;
    @ElementCollection
    private List<String> qualifications;
    @OneToOne
    private User postedBy;
}
