package com.care.server.models.seeker;

import com.care.server.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SeekerSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String currentStatus;
    private String currentCity;
    private String about;
    @OneToOne
    private User user;
}
