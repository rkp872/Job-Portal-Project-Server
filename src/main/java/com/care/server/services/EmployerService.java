package com.care.server.services;

import com.care.server.models.User;
import com.care.server.models.employer.NewJob;
import com.care.server.repo.UserRepository;
import com.care.server.repo.employer.NewJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployerService {
    @Autowired
    private NewJobRepository newJobRepository;
    @Autowired
    private UserRepository userRepository;
    public boolean addNewJob(NewJob newJob,String email){
        User user=userRepository.findByEmail(email);
        newJob.setPostedBy(user);
        newJobRepository.save(newJob);
        return true;
    }
}
