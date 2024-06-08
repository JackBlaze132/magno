package com.unibague.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_identification", unique = true, nullable = false)
    private String userIdentification;

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

    public User(String userIdentification, String email, Boolean isExternalUser){
        this.userIdentification = userIdentification;
        this.email = email;
        this.isExternalUser = isExternalUser;
    }

}
