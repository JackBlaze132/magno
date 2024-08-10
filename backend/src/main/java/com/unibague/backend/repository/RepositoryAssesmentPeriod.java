package com.unibague.backend.repository;

import com.unibague.backend.model.AssesmentPeriod;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAssesmentPeriod extends JpaRepository<AssesmentPeriod, Long>{

    @Modifying
    @Query("DELETE FROM AssesmentPeriod ap WHERE ap.id = :id")
    @Cascade({ CascadeType.REMOVE })
    void deleteAssessmentPeriod(@Param("id") Long id);
}
