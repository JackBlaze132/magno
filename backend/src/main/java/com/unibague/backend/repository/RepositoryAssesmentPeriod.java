package com.unibague.backend.repository;

import com.unibague.backend.model.AssesmentPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAssesmentPeriod extends JpaRepository<AssesmentPeriod, Long>{
}
