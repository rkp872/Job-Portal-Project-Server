package com.care.server.repo.seeker;

import com.care.server.models.User;
import com.care.server.models.seeker.SeekerCertification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeekerCertificationRepository extends JpaRepository<SeekerCertification,Long> {
    public List<SeekerCertification>  findByUser(User user);
}
