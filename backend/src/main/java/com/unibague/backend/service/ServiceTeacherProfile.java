package com.unibague.backend.service;

import com.unibague.backend.model.FunctionaryProfile;
import com.unibague.backend.repository.RepositoryAssesmentPeriod;
import com.unibague.backend.repository.RepositoryDependency;
import com.unibague.backend.repository.RepositoryFunctionaryProfile;
import com.unibague.backend.repository.RepositoryUser;
import com.unibague.backend.util.FetchExternalData;
import com.unibague.backend.util.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceTeacherProfile {

    @Autowired
    private RepositoryFunctionaryProfile repositoryFunctionaryProfile;

    @Autowired
    RepositoryAssesmentPeriod repositoryAssesmentPeriod;

    @Autowired
    RepositoryDependency repositoryDependency;

    @Autowired
    RepositoryUser repositoryUser;

    public Boolean addTeacherProfile(HashMap<String, String> functionaryProfile) {
        try{

            Map<String, Object> map = FetchExternalData.fetchExternalDataFromFunctionary(functionaryProfile.get("identification_number"));

            FunctionaryProfile f = new FunctionaryProfile();
            f.setIdentificationNumber(functionaryProfile.get("identification_number"));
            f.setAssesmentPeriod(repositoryAssesmentPeriod.findById(Long.valueOf(functionaryProfile.get("assesment_period_id"))).get());
            f.setDependency(repositoryDependency.findById(Long.valueOf(functionaryProfile.get("dependency_id"))).get());
            f.setUserTeacher(repositoryUser.findByUserIdentification(functionaryProfile.get("identification_number")).get());

            f.setEmail(String.valueOf(map.get("email")));
            f.setName(String.valueOf(map.get("full_name")));
            f.setPhoneNumber("1234567890");
            f.setSex(Sex.MALE);
            f.setUserCode(String.valueOf(map.get("code_user")));
            System.out.println(f.toString());
            repositoryFunctionaryProfile.save(f);

            return true;
        } catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
            return false;
        }
    }

    public List<FunctionaryProfile> getTeacherProfiles() {
        return repositoryFunctionaryProfile.findAll();
    }

    public List<FunctionaryProfile> getTeacherProfiles1() {
        return repositoryFunctionaryProfile.findAll1();
    }

    public List<FunctionaryProfile> getTeacherProfiles2() {
        return repositoryFunctionaryProfile.findAll2();
    }

    public FunctionaryProfile findResearchSeedbedCoordinator1() {
        return repositoryFunctionaryProfile.findResearchSeedbedCoordinator1();
    }

    public FunctionaryProfile findResearchSeedbedCoordinator2() {
        return repositoryFunctionaryProfile.findResearchSeedbedCoordinator2();
    }

    public FunctionaryProfile findInvestigationGroupDirector1(){
        return repositoryFunctionaryProfile.findInvestigationGroupDirector1();
    }
}
