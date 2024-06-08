package com.unibague.backend.repository;

import com.unibague.backend.model.FunctionaryProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    FunctionaryProfile findFunctionaryProfileById(Long id);
}
