package com.care.server.dto;

import com.care.server.models.User;
import com.care.server.models.seeker.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeekerProfileDto {
    private User user;
    private SeekerSummary seekerSummary;
    private List<SeekerAchievment> seekerAchievment;
    private List<SeekerCertification> seekerCertification;
    private SeekerContact seekerContact;
    private List<SeekerEducation> seekerEducation;
    private SeekerPersonal seekerPersonal;
    private List<SeekerProfessional> seekerProfessional;
}
