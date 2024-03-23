package com.unibague.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "dependencies")
public class Dependency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dependency", cascade = CascadeType.ALL)
    private List<TeacherProfile> teacherProfiles;
}
