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

    @Column(name = "name")
    private String name;

    @Column(name = "director_id")
    private Long directorId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "investigationGroup", cascade = CascadeType.ALL)
    private List<ResearchSeedbed> researchSeedbeds;

    public InvestigationGroup(String name, Long directorId){
        this.name = name;
        this.directorId = directorId;
    }

    public InvestigationGroup(){
    }

}
