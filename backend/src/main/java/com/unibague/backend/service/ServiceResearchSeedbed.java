package com.unibague.backend.service;

import com.unibague.backend.model.FunctionaryProfile;
import com.unibague.backend.model.ResearchSeedbed;
import com.unibague.backend.repository.RepositoryAssesmentPeriod;
import com.unibague.backend.repository.RepositoryFunctionaryProfile;
import com.unibague.backend.repository.RepositoryInvestigationGroup;
import com.unibague.backend.repository.RepositoryResearchSeedbed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ServiceResearchSeedbed {

    @Autowired
    RepositoryResearchSeedbed repositoryResearchSeedbed;

    @Autowired
    RepositoryAssesmentPeriod repositoryAssesmentPeriod;

    @Autowired
    RepositoryFunctionaryProfile repositoryFunctionaryProfile;

    @Autowired
    RepositoryInvestigationGroup repositoryInvestigationGroup;

    public List<ResearchSeedbed> getResearchSeedbeds(){
        return repositoryResearchSeedbed.findAll();
    }

    public List<ResearchSeedbed> getResearchSeedbeds1(){
        return repositoryResearchSeedbed.findAll1();
    }

    public Boolean addResearchSeedbed(HashMap<String, String> researchSeedbed) {
        try{
            ResearchSeedbed r = new ResearchSeedbed();
            ResearchSeedbed aux;

            FunctionaryProfile coordinator = repositoryFunctionaryProfile.findById(Long.parseLong(researchSeedbed.get("coordinator_fp_id"))).get();
            FunctionaryProfile tutor = repositoryFunctionaryProfile.findById(Long.parseLong(researchSeedbed.get("tutor_fp_id"))).get();

            r.setName(researchSeedbed.get("name"));
            r.setAssesmentPeriod(repositoryAssesmentPeriod.findById(Long.parseLong(researchSeedbed.get("assesment_period_id"))).get());
            r.setInvestigationGroup(repositoryInvestigationGroup.findById(Long.parseLong(researchSeedbed.get("investigation_group_id"))).get());
            r.setCoordinator(coordinator);
            r.setTutor(tutor);

            aux = repositoryResearchSeedbed.saveAndFlush(r);

            List<ResearchSeedbed> rsCoordinator = coordinator.getResearchSeedbeds_teacher();
            rsCoordinator.add(aux);
            repositoryFunctionaryProfile.save(coordinator);

            List<ResearchSeedbed> rsTutor = tutor.getResearchSeedbeds_teacher();
            rsTutor.add(aux);
            repositoryFunctionaryProfile.save(tutor);

            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
