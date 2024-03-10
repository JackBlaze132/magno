package com.unibague.backend.repository;

import com.unibague.backend.model.InvestigationGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryInvestigationGroup extends JpaRepository<InvestigationGroup, Long> {
}
