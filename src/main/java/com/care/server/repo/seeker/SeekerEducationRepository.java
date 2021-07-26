package com.care.server.repo.seeker;

import com.care.server.models.User;
import com.care.server.models.seeker.SeekerEducation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeekerEducationRepository extends JpaRepository<SeekerEducation,Long> {
    public List<SeekerEducation> findByUser(User user);
}
