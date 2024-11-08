package com.unibague.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "research_seedbeds")
@Getter
@Setter
public class ResearchSeedbed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToOne
    @JoinColumn(name = "coordinator_id")
    private FunctionaryProfile coordinator;

    @OneToOne
    @JoinColumn(name = "tutor_id")
    private FunctionaryProfile tutor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "investigation_group_id", nullable = false)
    private InvestigationGroup investigationGroup;

    @JsonIgnore
    @ManyToMany(mappedBy = "researchSeedbeds")
    private List<StudentProfile> studentsProfiles;

    @JsonIgnore
    @ManyToMany(mappedBy = "researchSeedbeds_teacher")
    private List<FunctionaryProfile> teachersProfiles;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assesment_period_id")
    private AssesmentPeriod assesmentPeriod;

}
