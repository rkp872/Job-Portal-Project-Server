package com.care.server.models.seeker;

import com.care.server.models.User;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SeekerCertification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String certTitle;
    private String issuer;
    private Date date;
    private String description;
    @OneToOne
    private User user;
}
