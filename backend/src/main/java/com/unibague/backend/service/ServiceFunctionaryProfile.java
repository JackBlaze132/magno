package com.unibague.backend.service;

import com.unibague.backend.model.AssesmentPeriod;
import com.unibague.backend.model.FunctionaryProfile;
import com.unibague.backend.model.ResearchSeedbed;
import com.unibague.backend.repository.*;
import com.unibague.backend.util.ExceptionLogger;
import com.unibague.backend.util.FetchExternalData;
import com.unibague.backend.util.IntegraFunctionaryNomenclature;
import com.unibague.backend.util.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceFunctionaryProfile {

    @Autowired
    private RepositoryFunctionaryProfile repositoryFunctionaryProfile;

    @Autowired
    RepositoryAssesmentPeriod repositoryAssesmentPeriod;

    @Autowired
    RepositoryDependency repositoryDependency;

    @Autowired
    RepositoryUser repositoryUser;

    @Autowired
    RepositoryResearchSeedbed repositoryResearchSeedbed;

    @Autowired
    ServiceUser serviceUser;

    /**
     * Method to add a functionary profile
     * @param functionaryProfile JSON with the keys identification_number and assesment_period_id
     * @return true if the functionary profile was added successfully, false otherwise
     */
    public Boolean addFunctionaryProfile(HashMap<String, String> functionaryProfile) {
        try{

            String identificationNumber = functionaryProfile.get("identification_number");
            Long assesmentPeriodId = Long.valueOf(functionaryProfile.get("assesment_period_id"));

            if(repositoryAssesmentPeriod.findById(assesmentPeriodId).isEmpty()){
                return false;
            }
            AssesmentPeriod assesmentPeriod = repositoryAssesmentPeriod.findById(assesmentPeriodId).get();
            if(repositoryFunctionaryProfile.findByIdentificationNumberAndAssesmentPeriod(identificationNumber, assesmentPeriod).isPresent()){
                return false;
            }
            Map<String, Object> map = FetchExternalData.fetchExternalDataFromFunctionary(identificationNumber);

            FunctionaryProfile f = new FunctionaryProfile();
            f.setIdentificationNumber(identificationNumber);
            f.setAssesmentPeriod(repositoryAssesmentPeriod.findById(Long.valueOf(functionaryProfile.get("assesment_period_id"))).get());
            f.setDependency(repositoryDependency.findByName(map.get(IntegraFunctionaryNomenclature.PROGRAM).toString()).get());

            if(repositoryUser.findByUserIdentification(identificationNumber).isEmpty()){
                //Notice that the following code don't consider the case when the user is an external user, needs to be corrected
                Map<String, Object> functionaryInfo = FetchExternalData.fetchExternalDataFromFunctionary(identificationNumber);

                HashMap<String, String> user = new HashMap<>();
                user.put("email", String.valueOf(functionaryInfo.get("email")));
                user.put("isExternalUser", "false");
                user.put("identification", String.valueOf(functionaryInfo.get("identification")));
                serviceUser.addUser(user);
                f.setUserTeacher(repositoryUser.findByUserIdentification(identificationNumber).get());
            }
            else{
                f.setUserTeacher(repositoryUser.findByUserIdentification(identificationNumber).get());
            }

            f.setEmail(String.valueOf(map.get("email")));
            f.setName(String.valueOf(map.get("full_name")));
            f.setPhoneNumber("1234567890");
            f.setSex(String.valueOf(map.get(IntegraFunctionaryNomenclature.SEX)).equals("F") ? Sex.FEMALE : Sex.MALE);
            f.setUserCode(String.valueOf(map.get("code_user")));
            repositoryFunctionaryProfile.save(f);

            return true;
        } catch (Exception e) {
            ExceptionLogger.logException(e);
            System.out.printf("Error: %s", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Boolean addFunctionaryProfileToAResearchSeedbed(HashMap<String, String> functionaryProfile){
        try{
            FunctionaryProfile f = repositoryFunctionaryProfile.findById(Long.valueOf(functionaryProfile.get("functionary_profile_id"))).get();
            List<ResearchSeedbed> researchSeedbeds = f.getResearchSeedbeds_teacher();
            researchSeedbeds.add(repositoryResearchSeedbed.findById(Long.valueOf(functionaryProfile.get("research_seedbed_id"))).get());
            f.setResearchSeedbeds_teacher(researchSeedbeds);
            repositoryFunctionaryProfile.save(f);
            return true;
        }
        catch (Exception e){
            ExceptionLogger.logException(e);
            System.out.printf("Error: %s", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public FunctionaryProfile getCoordinatorByResearchSeedbedId(Long researchseedbedId) {
        return repositoryFunctionaryProfile.findCoordinatorByResearchSeedbedId(researchseedbedId);
    }

    public FunctionaryProfile getTutorByResearchSeedbedId(Long researchseedbedId) {
        return repositoryFunctionaryProfile.findTutorByResearchSeedbedId(researchseedbedId);
    }

    public List<FunctionaryProfile> getExternalFunctionaryProfilesByResearchSeedbedId(Long researchseedbedId) {
        return repositoryFunctionaryProfile.findExternalFunctionaryProfilesByResearchSeedbedId(researchseedbedId);
    }

    public List<FunctionaryProfile> getFunctionaryProfiles() {
        return repositoryFunctionaryProfile.findAll();
    }

    public List<FunctionaryProfile> getFunctionaryProfilesByAssesmentPeriod(Long idAssesmentPeriod) {
        return repositoryFunctionaryProfile.findFunctionaryProfilesByAssesmentPeriodId(idAssesmentPeriod);
    }
}
