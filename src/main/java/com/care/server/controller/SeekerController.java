package com.care.server.controller;

import com.care.server.models.employer.NewJob;
import com.care.server.models.seeker.*;
import com.care.server.services.SeekerSeervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/seeker")
public class SeekerController {
    @Autowired
    private SeekerSeervice seekerSeervice;

    @GetMapping("/get-contact")
    public SeekerContact getContact(Principal principal){
        return seekerSeervice.getContact(principal.getName());
    }

    @PostMapping("/update-contact")
    public String updateContact(@RequestBody SeekerContact seekerContact, Principal principal){
        boolean result = seekerSeervice.updateContact(seekerContact, principal.getName());
        return "Updated SUccessfully";
    }
    @PostMapping("/add-professional")
    public String saveProfessionalData(@RequestBody SeekerProfessional seekerProfessional,Principal principal){
        seekerSeervice.saveProfessionalData(seekerProfessional,principal.getName());
        return "Saved Successfully";
    }
    @GetMapping("/get-professional")
    public List<SeekerProfessional> getProfessionalData(Principal principal){
        return seekerSeervice.getProfessionalData(principal.getName());
    }
    @GetMapping("delete-professional/{id}")
    public String deleteProfessionalData(@PathVariable("id") long id,Principal principal){
        System.out.println("id : "+id);
        if(seekerSeervice.deleteProfssionalData(id,principal.getName())) return "deleted";
        else return "Invalid Request";
    }
    @PostMapping("/add-education")
    public String addEducationData(@RequestBody SeekerEducation seekerEducation,Principal principal){
        if(seekerSeervice.addEducationData(seekerEducation,principal.getName()))
            return "Added";
        else
            return "Server Error";

    }
    @GetMapping("/delete-education/{id}")
    public String deleteEducation(@PathVariable("id") long id,Principal principal){
        if(seekerSeervice.deleteEducationData(id,principal.getName())) return "Deleted";
        else return "Invalid Request";
    }
    @GetMapping("/get-education")
    public List<SeekerEducation> fetchEducationData(Principal principal){
        return seekerSeervice.getEducationData(principal.getName() );
    }
    @PostMapping("/add-certification")
    public String addCertification(@RequestBody SeekerCertification seekerCertification,Principal principal){
        if(seekerSeervice.addCertification(seekerCertification,principal.getName())) return "Added";
        else return "Server error";
    }
    @GetMapping("/delete-certification/{id}")
    public String deleteCertification(@PathVariable("id") long id, Principal principal){
        if(seekerSeervice.deleteCertification(id,principal.getName())) return "Deleted";
        else return "Invalid request";
    }
    @GetMapping("/get-certification")
    public List<SeekerCertification> getCertificates(Principal principal){
        return seekerSeervice.fetchCertificationData(principal.getName());
    }
    @GetMapping("/get-achievements")
    public List<SeekerAchievment> fetchAchievement(Principal principal){
        return seekerSeervice.fetchAchievements(principal.getName());
    }
    @PostMapping("/add-achievement")
    public String addAchievement(@RequestBody SeekerAchievment seekerAchievment,Principal principal){
        if(seekerSeervice.addAchievement(seekerAchievment,principal.getName())){
            return "Added";
        }
        else
            return "Server Error";
    }
    @GetMapping("/delete-achievement/{id}")
    public String deleteAchievement(@PathVariable("id") long id,Principal principal){
        if(seekerSeervice.deleteAchievement(id,principal.getName())) return "Deleted";
        else return "Invalid request";
    }
    @GetMapping("/get-personal")
    public SeekerPersonal fetchPersonalData(Principal principal){
        return seekerSeervice.fetchPersonalData(principal.getName());
    }
    @PostMapping("/update-personal")
    public String updatePersonalData(@RequestBody SeekerPersonal seekerPersonal, Principal principal){
        seekerSeervice.updatePersonalData(seekerPersonal,principal.getName());
        return "Updated";
    }
    @GetMapping("/get-summary")
    public SeekerSummary fetchSummary(Principal principal){
        return seekerSeervice.fetchSummary(principal.getName());
    }
    @PostMapping("/update-summary")
    public String updateSummary(@RequestBody SeekerSummary seekerSummary,Principal principal){
        if(seekerSeervice.updateSummary(seekerSummary,principal.getName())) return "Updated";
        else
            return "Invalid Request";
    }
    @GetMapping("/get-recommendedjobs")
    public List<NewJob> getRecommendedJobs(){
        return seekerSeervice.getRecommendedJobs();
    }
    @GetMapping("/apply-job/{id}")
    public String applyToJob(@PathVariable("id") long id,Principal principal){
        if(seekerSeervice.applyToJob(id,principal.getName())) return "You Applied For This Job Successfully";
        else return "You Already Applied For This Job";
    }
    @GetMapping("/numberof-applicants/{id}")
    public int getNumOfApplicants(@PathVariable("id") long id){
        return seekerSeervice.numberOfApplicants(id);
    }
    @GetMapping("/selected-jobs")
    public List<NewJob> getSelectedJob(Principal principal){
        return seekerSeervice.getSelectedJob(principal.getName());
    }
}
