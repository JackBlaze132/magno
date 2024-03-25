package com.unibague.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_code")
    private String userCode;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "is_external_user")
    private Boolean isExternalUser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userStudent", cascade = CascadeType.ALL)
    private List<StudentProfile> studentProfiles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userTeacher", cascade = CascadeType.ALL)
    private List<TeacherProfile> teacherProfiles;

    public User(){

    }

    public User(String userCode, String email, Boolean isExternalUser){
        this.userCode = userCode;
        this.email = email;
        this.isExternalUser = isExternalUser;
    }

}
