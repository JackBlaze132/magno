package com.unibague.backend.repository;

import com.unibague.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RepositoryUser extends JpaRepository<User, Long> {

    Optional<User> findByUserIdentification(String userCode);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.email = :email, u.isExternalUser = :isExternalUser WHERE u.userIdentification = :userIdentification")
    int updateUserByUserIdentification(String email, Boolean isExternalUser, String userIdentification);
}
