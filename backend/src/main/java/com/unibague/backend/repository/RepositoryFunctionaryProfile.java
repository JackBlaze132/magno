package com.unibague.backend.repository;

import com.unibague.backend.model.AssesmentPeriod;
import com.unibague.backend.model.FunctionaryProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RepositoryFunctionaryProfile extends JpaRepository<FunctionaryProfile, Long> {

    Optional<FunctionaryProfile> findById(Long teacherProfileId);

    @Query("SELECT tp from FunctionaryProfile tp INNER JOIN tp.researchSeedbeds_teacher rs WHERE rs.id = 1")
    List<FunctionaryProfile> findAll1();

    @Query("SELECT tp from FunctionaryProfile tp INNER JOIN tp.researchSeedbeds_teacher rs WHERE rs.id = 2")
    List<FunctionaryProfile> findAll2();

    @Query("SELECT rs.coordinator from ResearchSeedbed rs WHERE rs.id = 1")
    FunctionaryProfile findResearchSeedbedCoordinator1();

    @Query("SELECT rs.coordinator from ResearchSeedbed rs WHERE rs.id = 2")
    FunctionaryProfile findResearchSeedbedCoordinator2();

    @Query("SELECT ig.coordinator from InvestigationGroup ig WHERE ig.id = 1")
    FunctionaryProfile findInvestigationGroupDirector1();

    @Query("SELECT rs.coordinator from ResearchSeedbed rs WHERE rs.id = ?1")
    FunctionaryProfile findCoordinatorByResearchSeedbedId(Long id);

    @Query("SELECT rs.tutor from ResearchSeedbed rs WHERE rs.id = ?1")
    FunctionaryProfile findTutorByResearchSeedbedId(Long id);

    @Query("SELECT fp from FunctionaryProfile fp INNER JOIN fp.researchSeedbeds_teacher rs WHERE rs.id = ?1 AND fp.userTeacher.isExternalUser = true")
    List<FunctionaryProfile> findExternalFunctionaryProfilesByResearchSeedbedId(Long id);

    @Query("SELECT fp from FunctionaryProfile fp WHERE fp.assesmentPeriod.id = ?1")
    List<FunctionaryProfile> findFunctionaryProfilesByAssesmentPeriodId(Long id);

    @Query("SELECT COUNT(DISTINCT fp) " +
            "FROM FunctionaryProfile fp " +
            "LEFT JOIN fp.researchSeedbeds_teacher rs " +
            "LEFT JOIN fp.investigationGroup ig " +
            "WHERE fp.assesmentPeriod = :assesmentPeriod " +
            "AND (rs IS NOT NULL OR ig IS NOT NULL) " +
            "GROUP BY fp.assesmentPeriod")
    Long countFunctionariesInAnAssessmentPeriod(@Param("assesmentPeriod") AssesmentPeriod assesmentPeriod);
}
