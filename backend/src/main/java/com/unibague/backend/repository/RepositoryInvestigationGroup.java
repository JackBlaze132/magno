package com.unibague.backend.repository;

import com.unibague.backend.model.InvestigationGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryInvestigationGroup extends JpaRepository<InvestigationGroup, Long> {

    @Query("SELECT ig from InvestigationGroup ig WHERE ig.assesmentPeriod.id = ?1")
    List<InvestigationGroup> findByAssesmentPeriodId(Long assesmentPeriodId);

    // Most recent investigation groups will appear first
    @Query("SELECT ig FROM InvestigationGroup ig ORDER BY ig.assesmentPeriod.endDate DESC")
    List<InvestigationGroup> findAllOrderedByAssesmentPeriod();
}
