package com.care.server.repo.seeker;

import com.care.server.models.User;
import com.care.server.models.seeker.SeekerPersonal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeekerPersonalRepository extends JpaRepository<SeekerPersonal,Long> {
    public SeekerPersonal findByUser(User user);
}
