package com.unibague.backend.repository;

import com.unibague.backend.model.AcademicProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAcademicProgram extends JpaRepository<AcademicProgram, Long> {
}
