package com.care.server.models.seeker;

import com.care.server.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SeekerPersonal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fatherName;
    private String motherName;
    private String dob;
    private String languageKnown;
    private String hobby;
    private String relocation;
    private String aadharNumber;
    private String panNumber;
    @OneToOne
    private User user;
}
