package com.unibague.backend.repository;

import com.unibague.backend.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
