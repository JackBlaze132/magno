package com.unibague.backend.repository;

import com.unibague.backend.model.Dependency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryDependency extends JpaRepository<Dependency, Long>{

    Optional<Dependency> findById(Long id);
    Optional<Dependency> findByName(String name);
    Boolean existsByName(String name);
}
