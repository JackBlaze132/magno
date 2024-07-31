package com.unibague.backend.service;

import com.unibague.backend.model.AssesmentPeriod;
import com.unibague.backend.model.FunctionaryProfile;
import com.unibague.backend.model.InvestigationGroup;
import com.unibague.backend.model.ResearchSeedbed;
import com.unibague.backend.repository.RepositoryAssesmentPeriod;
import com.unibague.backend.repository.RepositoryFunctionaryProfile;
import com.unibague.backend.repository.RepositoryInvestigationGroup;
import com.unibague.backend.repository.RepositoryResearchSeedbed;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     * Method to add a research seedbed, it receives a map with the following keys:
     * name, coordinator_fp_id, tutor_fp_id, investigation_group_id
     * @param map JSON with the keys mentioned above
     * @return true if the research seedbed was created successfully, false otherwise
     */
    @Transactional
    public Boolean addResearchSeedbed(HashMap<String, String> map) {
        try{

            String name = map.get("name");
            Long coordinatorId = Long.parseLong(map.get("coordinator_fp_id"));
            Long tutorId = Long.parseLong(map.get("tutor_fp_id"));
            Long investigationGroupId = Long.parseLong(map.get("investigation_group_id"));

            boolean coordinatorExists = repositoryFunctionaryProfile.findById(coordinatorId).isPresent();
            boolean tutorExists = repositoryFunctionaryProfile.findById(tutorId).isPresent();
            boolean investigationGroupExists = repositoryInvestigationGroup.findById(investigationGroupId).isPresent();

            if(!coordinatorExists || !tutorExists || !investigationGroupExists){
                return false;
            }

            FunctionaryProfile coordinator = repositoryFunctionaryProfile.findById(coordinatorId).get();
            FunctionaryProfile tutor = repositoryFunctionaryProfile.findById(tutorId).get();
            InvestigationGroup ig = repositoryInvestigationGroup.findById(investigationGroupId).get();

            if(repositoryResearchSeedbed.findByNameAndAssesmentPeriod(name, ig.getAssesmentPeriod()).isPresent()){
                return false;
            }

            ResearchSeedbed r = new ResearchSeedbed();
            r.setActive(true);

            r.setName(name);
            r.setInvestigationGroup(ig);
            r.setAssesmentPeriod(ig.getAssesmentPeriod());
            r.setCoordinator(coordinator);
            r.setTutor(tutor);

            //Auxiliary variable to save the research seedbed when it is created and then save it in the functionary profile
            ResearchSeedbed aux = repositoryResearchSeedbed.saveAndFlush(r);

            List<ResearchSeedbed> rsCoordinator = new ArrayList<>(coordinator.getResearchSeedbeds_teacher());
            rsCoordinator.add(aux);
            coordinator.setResearchSeedbeds_teacher(rsCoordinator);
            repositoryFunctionaryProfile.save(coordinator);

            List<ResearchSeedbed> rsTutor = new ArrayList<>(tutor.getResearchSeedbeds_teacher());
            rsTutor.add(aux);
            tutor.setResearchSeedbeds_teacher(rsTutor);
            repositoryFunctionaryProfile.save(tutor);

            return true;
        }
        catch(Exception e){
            System.out.println("Failure trying to create a research seedbed:\n" + e.getMessage());
            return false;
        }
    }

    /**
     * Method to update the coordinator of a research seedbed, it receives a map with the following keys:
     * research_seedbed_id, new_coordinator_fp_id
     * @param map JSON with the keys mentioned above
     * @return true if the coordinator was updated successfully, false otherwise
     */
    public Boolean updateResearchSeedbedCoordinator(HashMap<String, String> map){

        Long researchSeedbedId = Long.parseLong(map.get("research_seedbed_id"));
        Long newCoordinatorId = Long.parseLong(map.get("new_coordinator_fp_id"));

        boolean researchSeedbedExists = repositoryResearchSeedbed.findById(researchSeedbedId).isPresent();
        boolean newCoordinatorExists = repositoryFunctionaryProfile.findById(newCoordinatorId).isPresent();

        if (!researchSeedbedExists || !newCoordinatorExists) {
            return false;
        }

        ResearchSeedbed rs = repositoryResearchSeedbed.findById(researchSeedbedId).get();
        FunctionaryProfile newCoordinator = repositoryFunctionaryProfile.findById(newCoordinatorId).get();

        // Check if the assessment period of the new coordinator is the same as the research seedbed
        if(!rs.getAssesmentPeriod().equals(newCoordinator.getAssesmentPeriod())){
            return false;
        }

        rs.setCoordinator(newCoordinator);
        repositoryResearchSeedbed.save(rs);

        return true;
    }

    public List<ResearchSeedbed> getResearchSeedbedsByInvestigationGroupId(Long id){
        if(repositoryResearchSeedbed.findByInvestigationGroupId(id).isEmpty()){
            return null;
        }
        else{
            return repositoryResearchSeedbed.findByInvestigationGroupId(id).get();
        }
    }

    public ResearchSeedbed getResearchSeedbedById(Long id){
        if(repositoryResearchSeedbed.findById(id).isEmpty()){
            return null;
        }
        else{
            return repositoryResearchSeedbed.findById(id).get();
        }
    }
}
