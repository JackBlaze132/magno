package com.unibague.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unibague.backend.util.Sex;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_code")
    private String userCode;

    @Column(name = "identification_number")
    private String identificationNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "sex")
    private Sex sex;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "research_seedbeds_students_teachers_profiles",
            joinColumns = @JoinColumn(name = "student_profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "research_seedbed_id", referencedColumnName = "id")
    )
    private List<ResearchSeedbed> researchSeedbeds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_program_id")
    private AcademicProgram academicProgram;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userStudent;
}
