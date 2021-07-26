package com.care.server.repo.seeker;

import com.care.server.models.User;
import com.care.server.models.seeker.SeekerProfessional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeekerProfessionalRepository extends JpaRepository<SeekerProfessional,Long> {
    public List<SeekerProfessional> findByUser(User user);
}
