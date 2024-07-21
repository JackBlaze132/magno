package com.unibague.backend.service;

import com.unibague.backend.model.FunctionaryProfile;
import com.unibague.backend.model.InvestigationGroup;
import com.unibague.backend.model.ResearchSeedbed;
import com.unibague.backend.repository.RepositoryAssesmentPeriod;
import com.unibague.backend.repository.RepositoryFunctionaryProfile;
import com.unibague.backend.repository.RepositoryInvestigationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ServiceInvestigationGroup {

    @Autowired
    private RepositoryInvestigationGroup repositoryInvestigationGroup;

    @Autowired
    RepositoryAssesmentPeriod repositoryAssesmentPeriod;

    @Autowired
    RepositoryFunctionaryProfile repositoryFunctionaryProfile;

    public List<InvestigationGroup> getInvestigationGroups() {
        return repositoryInvestigationGroup.findAll();
    }

    public boolean addInvestigationGroup(HashMap<String, String> investigationGroup) {
        try{

            InvestigationGroup i = new InvestigationGroup();
            FunctionaryProfile coordinator = repositoryFunctionaryProfile.findById(Long.parseLong(investigationGroup.get("coordinator_fp_id"))).get();

            i.setName(investigationGroup.get("name"));
            i.setCoordinator(coordinator);
            i.setAssesmentPeriod(coordinator.getAssesmentPeriod());

            List<ResearchSeedbed> researchSeedbeds = new ArrayList<>();
            i.setResearchSeedbeds(researchSeedbeds);

            repositoryInvestigationGroup.save(i);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<InvestigationGroup> findInvestigationGroupByAssesmentPeriod(Long assesmentPeriodId) {
        return repositoryInvestigationGroup.findByAssesmentPeriodId(assesmentPeriodId);
    }

    public List<InvestigationGroup> findAllOrderedByAssesmentPeriod(){
        return repositoryInvestigationGroup.findAllOrderedByAssesmentPeriod();
    }
}
