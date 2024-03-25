package com.unibague.backend.service;

import com.unibague.backend.model.StudentProfile;
import com.unibague.backend.repository.RepositoryStudentProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceStudentProfile {

    @Autowired
    private RepositoryStudentProfile repositoryStudentProfile;

    public Boolean addStudentProfile(StudentProfile studentProfile) {
        try{
            repositoryStudentProfile.save(studentProfile);
            return true;
        } catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
            return false;
        }
    }

    public List<StudentProfile> getStudentProfiles() {
        return (List<StudentProfile>) repositoryStudentProfile.findAll();
    }
}
