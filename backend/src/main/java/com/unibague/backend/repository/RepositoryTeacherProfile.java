package com.unibague.backend.repository;

import com.unibague.backend.model.TeacherProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryTeacherProfile extends JpaRepository<TeacherProfile, Long> {

    Optional<TeacherProfile> findById(Long teacherProfileId);
}
