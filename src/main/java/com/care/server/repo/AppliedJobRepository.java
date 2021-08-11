package com.care.server.repo;

import com.care.server.models.AppliedJob;
import com.care.server.models.User;
import com.care.server.models.employer.NewJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppliedJobRepository extends JpaRepository<AppliedJob,Long> {
    AppliedJob findByNewJobAndUser(NewJob newJob, User user);
    int countByNewJob(NewJob newJob);
    List<AppliedJob> findByNewJob(@Param("newJob") NewJob newJob);

}
