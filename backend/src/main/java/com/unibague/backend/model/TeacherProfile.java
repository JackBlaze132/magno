package com.unibague.backend.model;

import com.unibague.backend.util.Sex;
import jakarta.persistence.*;

@Entity
@Table(name = "teacher_profiles")
public class TeacherProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String userCode;

    private String identificationNumber;

    private String email;

    private String phoneNumber;

    private Sex sex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dependency_id")
    private Dependency dependency;
}
