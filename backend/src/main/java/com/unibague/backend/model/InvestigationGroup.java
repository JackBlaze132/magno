package com.unibague.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "investigation_groups")
@Data
public class InvestigationGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "coordinator_id", nullable = false)
    private Long coordinatorId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "investigationGroup", cascade = CascadeType.ALL)
    private List<ResearchSeedbed> researchSeedbeds;

    public InvestigationGroup(String name, Long coordinatorId){
        this.name = name;
        this.coordinatorId = coordinatorId;
    }

    public InvestigationGroup(){
    }

}
