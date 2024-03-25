package com.unibague.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unibague.backend.util.Sex;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "teacher_profiles")
public class TeacherProfile {

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dependency_id")
    private Dependency dependency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userTeacher;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "research_seedbeds_students_teachers_profiles",
            joinColumns = @JoinColumn(name = "teacher_profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "research_seedbed_id", referencedColumnName = "id")
    )
    private List<ResearchSeedbed> researchSeedbeds_teacher;
}
