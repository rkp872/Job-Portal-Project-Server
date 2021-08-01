package com.care.server.repo.employer;

import com.care.server.models.employer.NewJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewJobRepository extends JpaRepository<NewJob,Long> {
}
