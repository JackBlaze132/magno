package com.unibague.backend.repository;

import com.unibague.backend.model.AssesmentPeriod;
import com.unibague.backend.model.ResearchSeedbed;
import com.unibague.backend.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryStudentProfile extends JpaRepository<StudentProfile, Long>{


    @Query("SELECT sp from StudentProfile sp INNER JOIN sp.researchSeedbeds rs WHERE rs.id = 1")
    List<StudentProfile> findAll1();

    @Query("SELECT sp from StudentProfile sp INNER JOIN sp.researchSeedbeds rs WHERE rs.id = 2")
    List<StudentProfile> findAll2();

    @Query("SELECT sp from StudentProfile sp INNER JOIN sp.researchSeedbeds rs WHERE rs.id = ?1")
    List<StudentProfile> findAllByResearchSeedbedId(Long id);

    @Query("SELECT sp from StudentProfile sp INNER JOIN sp.userStudent u WHERE u.userIdentification = ?1")
    List<StudentProfile> findByUserIdentification(Long id);

    @Query("SELECT sp from StudentProfile sp WHERE sp.userStudent.userIdentification = ?1 AND sp.assesmentPeriod.id = ?2")
    Optional<StudentProfile> findByUserIdentificationAndAssesmentPeriodId(String identification, Long assesmentPeriodId);

    @Query("SELECT rs from ResearchSeedbed rs INNER JOIN rs.studentsProfiles sp WHERE sp.userStudent.userIdentification = ?1")
    Optional<List<ResearchSeedbed>> getStudentProfileResearchSeedbeds(String identification);

    @Query("SELECT COUNT(DISTINCT sp) " +
            "FROM StudentProfile sp " +
            "JOIN sp.researchSeedbeds rs " +
            "WHERE sp.assesmentPeriod = :assesmentPeriod " +
            "GROUP BY sp.assesmentPeriod " +
            "HAVING COUNT(rs) > 0")
    Long countStudentsInAnAssessmentPeriod(@Param("assesmentPeriod") AssesmentPeriod assesmentPeriod);
}
