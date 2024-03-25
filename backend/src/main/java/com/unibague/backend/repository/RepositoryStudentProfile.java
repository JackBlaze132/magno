package com.unibague.backend.repository;

import com.unibague.backend.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryStudentProfile extends JpaRepository<StudentProfile, Long>{
}
