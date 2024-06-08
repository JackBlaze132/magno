package com.unibague.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "investigation_groups")
@Getter
@Setter
public class InvestigationGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "coordinator_id")
    private FunctionaryProfile coordinator;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "investigationGroup", cascade = CascadeType.ALL)
    private List<ResearchSeedbed> researchSeedbeds;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assesment_period_id")
    private AssesmentPeriod assesmentPeriod;

    public InvestigationGroup(){
    }

}
