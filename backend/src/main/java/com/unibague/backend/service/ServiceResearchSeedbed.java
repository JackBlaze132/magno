package com.unibague.backend.service;

import com.unibague.backend.model.FunctionaryProfile;
import com.unibague.backend.model.InvestigationGroup;
import com.unibague.backend.model.ResearchSeedbed;
import com.unibague.backend.model.StudentProfile;
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

    @Autowired
    ServiceStudentProfile serviceStudentProfile;

    @Autowired
    ServiceFunctionaryProfile serviceFunctionaryProfile;

    public List<ResearchSeedbed> getResearchSeedbeds() {
        return repositoryResearchSeedbed.findAll();
    }

    /**
     * Method to add a research seedbed, it receives a map with the following keys:
     * name, coordinator_fp_id, tutor_fp_id, investigation_group_id
     *
     * @param map JSON with the keys mentioned above
     * @return true if the research seedbed was created successfully, false otherwise
     */
    @Transactional
    public Boolean addResearchSeedbed(HashMap<String, String> map) {
        try {

            String name = map.get("name");
            Long coordinatorId = Long.parseLong(map.get("coordinator_fp_id"));
            Long tutorId = Long.parseLong(map.get("tutor_fp_id"));
            Long investigationGroupId = Long.parseLong(map.get("investigation_group_id"));

            boolean coordinatorExists = repositoryFunctionaryProfile.findById(coordinatorId).isPresent();
            boolean tutorExists = repositoryFunctionaryProfile.findById(tutorId).isPresent();
            boolean investigationGroupExists = repositoryInvestigationGroup.findById(investigationGroupId).isPresent();

            if (!coordinatorExists || !tutorExists || !investigationGroupExists) {
                return false;
            }

            FunctionaryProfile coordinator = repositoryFunctionaryProfile.findById(coordinatorId).get();
            FunctionaryProfile tutor = repositoryFunctionaryProfile.findById(tutorId).get();
            InvestigationGroup ig = repositoryInvestigationGroup.findById(investigationGroupId).get();

            if (repositoryResearchSeedbed.findByNameAndAssesmentPeriod(name, ig.getAssesmentPeriod()).isPresent()) {
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
        } catch (Exception e) {
            System.out.println("Failure trying to create a research seedbed:\n" + e.getMessage());
            return false;
        }
    }

    /**
     * Method to update the coordinator or tutor of a research seedbed, it receives a map with the following keys:
     * research_seedbed_id, new_functionary_fp_id, role (COORDINATOR o TUTOR)
     * Notice that if this method fails trying to update a tutor that is already a tutor in another research seedbed
     * it's important to review the constraints in the database, because in some cases it could be necessary to fix a
     * unique constraint violation in the database because it is generated automatically
     *
     * @param map JSON with the keys mentioned above
     * @return true if the functionary was updated successfully, false otherwise
     */
    public Boolean updateResearchSeedbedFunctionary(HashMap<String, String> map) {

        Long researchSeedbedId = Long.parseLong(map.get("research_seedbed_id"));
        Long newFunctionaryId = Long.parseLong(map.get("new_functionary_fp_id"));
        String role = map.get("role");

        boolean researchSeedbedExists = repositoryResearchSeedbed.findById(researchSeedbedId).isPresent();
        boolean newFunctionaryExists = repositoryFunctionaryProfile.findById(newFunctionaryId).isPresent();

        if (!researchSeedbedExists || !newFunctionaryExists) {
            return false;
        }

        ResearchSeedbed rs = repositoryResearchSeedbed.findById(researchSeedbedId).get();
        FunctionaryProfile newFunctionary = repositoryFunctionaryProfile.findById(newFunctionaryId).get();

        // Check if the assessment period of the new functionary is the same as the research seedbed
        if (!rs.getAssesmentPeriod().getId().equals(newFunctionary.getAssesmentPeriod().getId())) {
            return false;
        }

        switch (role) {
            case "COORDINATOR":
                if (rs.getTutor().equals(newFunctionary)) {
                    return false; // The new coordinator is the current tutor
                }
                rs.setCoordinator(newFunctionary);
                break;
            case "TUTOR":
                if (rs.getCoordinator().equals(newFunctionary)) {
                    return false; // The new tutor is the current coordinator
                }
                rs.setTutor(newFunctionary);
                break;
            default:
                return false; // Invalid role
        }

        repositoryResearchSeedbed.save(rs);

        return true;
    }

    public List<ResearchSeedbed> getResearchSeedbedsByInvestigationGroupId(Long id) {
        if (repositoryResearchSeedbed.findByInvestigationGroupId(id).isEmpty()) {
            return null;
        }
        return repositoryResearchSeedbed.findByInvestigationGroupId(id).get();
    }

    public ResearchSeedbed getResearchSeedbedById(Long id) {
        if (repositoryResearchSeedbed.findById(id).isEmpty()) {
            return null;
        }
        return repositoryResearchSeedbed.findById(id).get();
    }

    /**
     * Method to update the name of a research seedbed, it receives a map with the following keys:
     * research_seedbed_id, new_name
     * @param map JSON with the keys mentioned above
     * @return true if the name was updated successfully, false otherwise
     */
    public Boolean updateResearchSeedbedName(HashMap<String, String> map) {
        Long researchSeedbedId = Long.parseLong(map.get("research_seedbed_id"));
        String newName = map.get("new_name");

        boolean researchSeedbedExists = repositoryResearchSeedbed.findById(researchSeedbedId).isPresent();

        if (!researchSeedbedExists) {
            return false;
        }

        ResearchSeedbed rs = repositoryResearchSeedbed.findById(researchSeedbedId).get();
        Long assessmentPeriodId = rs.getAssesmentPeriod().getId();

        if(repositoryResearchSeedbed.findResearchSeedbedsByAssesmentPeriodId(assessmentPeriodId).isPresent()){
            List<ResearchSeedbed> researchSeedbeds = repositoryResearchSeedbed.findResearchSeedbedsByAssesmentPeriodId(assessmentPeriodId).get();
            for (ResearchSeedbed researchSeedbed : researchSeedbeds) {
                if(researchSeedbed.getName().equalsIgnoreCase(newName)){
                    return false;
                }
            }
        }
        rs.setName(newName);

        repositoryResearchSeedbed.save(rs);

        return true;
    }

    @Transactional
    public Boolean deleteResearchSeedbed(HashMap<String, String> map) {
        try{
            Long researchSeedbedId = Long.parseLong(map.get("research_seedbed_id"));
            if(repositoryResearchSeedbed.findById(researchSeedbedId).isPresent()){
                repositoryResearchSeedbed.deleteResearchSeedbed(researchSeedbedId);
                return true;
            }
            return false;
        }
        catch (Exception e){
            System.out.println("Failure trying to delete a research seedbed:\n" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
