package com.unibague.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "user_code", unique = true)
    private String userCode;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "is_external_user", nullable = false)
    private Boolean isExternalUser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userStudent", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StudentProfile> studentProfiles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userTeacher", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<FunctionaryProfile> functionaryProfiles;

    public User(){

    }

    public User(String userCode, String email, Boolean isExternalUser){
        this.userCode = userCode;
        this.email = email;
        this.isExternalUser = isExternalUser;
    }

}
