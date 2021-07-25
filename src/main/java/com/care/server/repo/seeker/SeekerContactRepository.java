package com.care.server.repo.seeker;

import com.care.server.models.User;
import com.care.server.models.seeker.SeekerContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeekerContactRepository extends JpaRepository<SeekerContact ,Long> {
    SeekerContact findByUser(User user);
}
