package com.unibague.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unibague.backend.util.Sex;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "functionary_profiles")
public class FunctionaryProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String userCode;

    private String identificationNumber;

    private String email;

    private String phoneNumber;

    private Sex sex;

    @OneToOne(mappedBy = "coordinator", cascade = CascadeType.ALL)
    @JsonIgnore
    private InvestigationGroup investigationGroup;

    @OneToOne(mappedBy = "coordinator", cascade = CascadeType.ALL)
    @JsonIgnore
    private ResearchSeedbed researchSeedbed;

    @OneToOne(mappedBy = "tutor", cascade = CascadeType.ALL)
    @JsonIgnore
    private ResearchSeedbed tutor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dependency_id")
    private Dependency dependency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userTeacher;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "research_seedbeds_teachers_profiles",
            joinColumns = @JoinColumn(name = "teacher_profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "research_seedbed_id", referencedColumnName = "id")
    )
    private List<ResearchSeedbed> researchSeedbeds_teacher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assesment_period_id")
    private AssesmentPeriod assesmentPeriod;
}