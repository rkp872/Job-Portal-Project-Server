package com.care.server.repo.seeker;

import com.care.server.models.User;
import com.care.server.models.seeker.SeekerAchievment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeekerAchievementRepository extends JpaRepository<SeekerAchievment,Long> {
    public List<SeekerAchievment> findByUser(User user);
}
