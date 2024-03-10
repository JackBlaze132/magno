package com.unibague.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "research_seedbeds")
@Data
public class ResearchSeedbed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "coordinator_id")
    private Long coordinatorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investigation_group_id")
    private InvestigationGroup investigationGroup;

    @ManyToMany(mappedBy = "researchSeedbeds")
    private List<User> users;

    public ResearchSeedbed(String name, Long coordinatorId, InvestigationGroup investigationGroup){
        this.name = name;
        this.coordinatorId = coordinatorId;
        this.investigationGroup = investigationGroup;
    }

    public ResearchSeedbed(){
    }

}
