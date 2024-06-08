package com.unibague.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "academic_programs")
@Getter
@Setter
public class AcademicProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "program_code", nullable = false, unique = true)
    private String programCode;

    @JsonIgnore
    @ManyToMany(mappedBy = "academicPrograms")
    private List<StudentProfile> studentProfiles;
}
