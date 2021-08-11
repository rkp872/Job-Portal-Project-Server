package com.care.server.repo.employer;

import com.care.server.models.User;
import com.care.server.models.employer.NewJob;
import com.care.server.models.employer.SelectCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SelectCandidateRepository extends JpaRepository<SelectCandidate,Long> {
    SelectCandidate findByNewJob(NewJob newJob);
    List<SelectCandidate> findByUser(User user);
    SelectCandidate findByNewJobAndUser(NewJob newJob, User user);
}
