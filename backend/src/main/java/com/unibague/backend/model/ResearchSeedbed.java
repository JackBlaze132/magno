package com.unibague.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "coordinator_id", nullable = false)
    private Long coordinatorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investigation_group_id", nullable = false)
    private InvestigationGroup investigationGroup;

    @JsonIgnore
    @ManyToMany(mappedBy = "researchSeedbeds")
    private List<StudentProfile> studentsProfiles;

    @JsonIgnore
    @ManyToMany(mappedBy = "researchSeedbeds_teacher")
    private List<TeacherProfile> teachersProfiles;

    public ResearchSeedbed(String name, Long coordinatorId, InvestigationGroup investigationGroup){
        this.name = name;
        this.coordinatorId = coordinatorId;
        this.investigationGroup = investigationGroup;
    }

    public ResearchSeedbed(){
    }

}
