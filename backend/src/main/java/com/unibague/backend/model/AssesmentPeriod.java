package com.unibague.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name = "assesment_periods")
public class AssesmentPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assesmentPeriod", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StudentProfile> studentProfiles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assesmentPeriod", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<FunctionaryProfile> functionaryProfiles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assesmentPeriod", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ResearchSeedbed> researchSeedbeds;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assesmentPeriod", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<InvestigationGroup> investigationGroups;

}
