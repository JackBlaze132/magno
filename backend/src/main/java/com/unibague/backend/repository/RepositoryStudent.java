package com.unibague.backend.repository;

import com.unibague.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryStudent extends JpaRepository<Student, Long> {
}
