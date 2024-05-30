package com.unibague.backend.service;

import com.unibague.backend.model.FunctionaryProfile;
import com.unibague.backend.repository.RepositoryTeacherProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTeacherProfile {

    @Autowired
    private RepositoryTeacherProfile repositoryTeacherProfile;

    public Boolean addTeacherProfile(FunctionaryProfile functionaryProfile) {
        try{
            repositoryTeacherProfile.save(functionaryProfile);
            return true;
        } catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
            return false;
        }
    }

    public List<FunctionaryProfile> getTeacherProfiles() {
        return repositoryTeacherProfile.findAll();
    }

    public List<FunctionaryProfile> getTeacherProfiles1() {
        return repositoryTeacherProfile.findAll1();
    }

    public List<FunctionaryProfile> getTeacherProfiles2() {
        return repositoryTeacherProfile.findAll2();
    }

    public FunctionaryProfile findResearchSeedbedCoordinator1() {
        return repositoryTeacherProfile.findResearchSeedbedCoordinator1();
    }

    public FunctionaryProfile findResearchSeedbedCoordinator2() {
        return repositoryTeacherProfile.findResearchSeedbedCoordinator2();
    }

    public FunctionaryProfile findInvestigationGroupDirector1(){
        return repositoryTeacherProfile.findInvestigationGroupDirector1();
    }
}
