package com.unibague.backend.service;

import com.unibague.backend.model.TeacherProfile;
import com.unibague.backend.repository.RepositoryTeacherProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTeacherProfile {

    @Autowired
    private RepositoryTeacherProfile repositoryTeacherProfile;

    public Boolean addTeacherProfile(TeacherProfile teacherProfile) {
        try{
            repositoryTeacherProfile.save(teacherProfile);
            return true;
        } catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
            return false;
        }
    }

    public List<TeacherProfile> getTeacherProfiles() {
        return repositoryTeacherProfile.findAll();
    }
}
