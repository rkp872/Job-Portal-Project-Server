package com.care.server.models.seeker;

import com.care.server.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SeekerAchievment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String achievements;
    @OneToOne
    private User user;
}
