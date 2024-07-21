package com.unibague.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unibague.backend.util.Sex;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "functionary_profiles")
public class FunctionaryProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "user_code", nullable = false)
    private String userCode;

    @Column(name = "identification_number", nullable = false)
    private String identificationNumber;

    @Column(name = "email", nullable = false)
    private String email;

    private String phoneNumber;

    @Column(name = "sex", nullable = false)
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
            name = "research_seedbeds_functionary_profiles",
            joinColumns = @JoinColumn(name = "teacher_profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "research_seedbed_id", referencedColumnName = "id")
    )
    private List<ResearchSeedbed> researchSeedbeds_teacher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assesment_period_id")
    private AssesmentPeriod assesmentPeriod;
}
