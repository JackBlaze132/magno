package com.unibague.backend.repository;

import com.unibague.backend.model.TeacherProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositoryTeacherProfile extends JpaRepository<TeacherProfile, Long> {

    Optional<TeacherProfile> findById(Long teacherProfileId);

    @Query("SELECT tp from TeacherProfile tp INNER JOIN tp.researchSeedbeds_teacher rs WHERE rs.id = 1")
    List<TeacherProfile> findAll1();

    @Query("SELECT tp from TeacherProfile tp INNER JOIN tp.researchSeedbeds_teacher rs WHERE rs.id = 2")
    List<TeacherProfile> findAll2();

    @Query("SELECT rs.coordinator from ResearchSeedbed rs WHERE rs.id = 1")
    TeacherProfile findResearchSeedbedCoordinator1();

    @Query("SELECT rs.coordinator from ResearchSeedbed rs WHERE rs.id = 2")
    TeacherProfile findResearchSeedbedCoordinator2();

    @Query("SELECT ig.coordinator from InvestigationGroup ig WHERE ig.id = 1")
    TeacherProfile findInvestigationGroupDirector1();
}
