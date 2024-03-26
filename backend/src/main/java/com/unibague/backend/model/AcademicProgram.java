package com.unibague.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "academic_programs")
@Data
public class AcademicProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "academicProgram", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StudentProfile> studentProfiles;
}
