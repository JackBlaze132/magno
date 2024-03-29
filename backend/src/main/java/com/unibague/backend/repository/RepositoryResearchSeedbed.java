package com.unibague.backend.repository;

import com.unibague.backend.model.ResearchSeedbed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryResearchSeedbed extends JpaRepository<ResearchSeedbed, Long> {

    @Query("SELECT rs from ResearchSeedbed rs WHERE rs.investigationGroup.id = 1")
    List<ResearchSeedbed> findAll1();
}
