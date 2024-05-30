package com.unibague.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unibague.backend.util.Sex;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "student_profiles")
@Data
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

    @Column(name = "semester")
    private Byte semester;

    @Column(name = "email")
    private String email;

    @Column(name = "was_active")
    private boolean wasActive;

    @Column(name = "sex")
    private Sex sex;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
                name = "research_seedbeds_students_profiles",
            joinColumns = @JoinColumn(name = "student_profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "research_seedbed_id", referencedColumnName = "id")
    )
    private List<ResearchSeedbed> researchSeedbeds;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "academic_programs_students_profiles",
            joinColumns = @JoinColumn(name = "student_profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "academic_program_id", referencedColumnName = "id")
    )
    private List<AcademicProgram> academicPrograms;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userStudent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assesment_period_id")
    private AssesmentPeriod assesmentPeriod;
}
