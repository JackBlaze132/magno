package com.unibague.backend.repository;

import com.unibague.backend.model.AssesmentPeriod;
import com.unibague.backend.model.ResearchSeedbed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryResearchSeedbed extends JpaRepository<ResearchSeedbed, Long> {

    @Query("SELECT rs from ResearchSeedbed rs WHERE rs.investigationGroup.id = ?1")
    Optional<List<ResearchSeedbed>> findByInvestigationGroupId(Long investigationGroupId);

    Optional<ResearchSeedbed> findByNameAndAssesmentPeriod(String name, AssesmentPeriod assesmentPeriod);
}
