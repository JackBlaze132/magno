package com.unibague.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "academic_programs")
public class AcademicProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "academicProgram", cascade = CascadeType.ALL)
    private List<StudentProfile> studentProfiles;
}
