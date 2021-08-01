package com.care.server.repo.employer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.care.server.models.User;
import com.care.server.models.employer.NewJob;

public interface NewJobRepository extends JpaRepository<NewJob, Long> {
	List<NewJob> findByPostedBy(User postedBy);
}
