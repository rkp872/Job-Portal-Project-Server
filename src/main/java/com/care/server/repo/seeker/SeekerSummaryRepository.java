package com.care.server.repo.seeker;

import com.care.server.models.User;
import com.care.server.models.seeker.SeekerSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeekerSummaryRepository extends JpaRepository<SeekerSummary,Long> {
    public SeekerSummary findByUser(User user);
}
