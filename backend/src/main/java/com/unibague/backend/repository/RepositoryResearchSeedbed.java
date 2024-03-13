package com.unibague.backend.repository;

import com.unibague.backend.model.ResearchSeedbed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryResearchSeedbed extends JpaRepository<ResearchSeedbed, Long> {
}