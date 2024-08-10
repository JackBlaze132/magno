package com.unibague.backend.service;

import com.unibague.backend.model.FunctionaryProfile;
import com.unibague.backend.model.InvestigationGroup;
import com.unibague.backend.model.ResearchSeedbed;
import com.unibague.backend.repository.RepositoryAssesmentPeriod;
import com.unibague.backend.repository.RepositoryFunctionaryProfile;
import com.unibague.backend.repository.RepositoryInvestigationGroup;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceInvestigationGroup {

    @Autowired
    private RepositoryInvestigationGroup repositoryInvestigationGroup;

    @Autowired
    RepositoryAssesmentPeriod repositoryAssesmentPeriod;

    @Autowired
    RepositoryFunctionaryProfile repositoryFunctionaryProfile;

    @Autowired
    ServiceUpload serviceUpload;

    @Autowired
    ServiceFunctionaryProfile serviceFunctionaryProfile;

    public List<InvestigationGroup> getInvestigationGroups() {
        return repositoryInvestigationGroup.findAll();
    }

    public boolean addInvestigationGroup(HashMap<String, String> investigationGroup) {
        try{

            Long coordinatorId = Long.parseLong(investigationGroup.get("coordinator_fp_id"));

            if(repositoryFunctionaryProfile.findById(coordinatorId).isEmpty()){
                return false;
            }

            InvestigationGroup i = new InvestigationGroup();
            FunctionaryProfile coordinator = repositoryFunctionaryProfile.findById(coordinatorId).get();

            i.setName(investigationGroup.get("name"));
            i.setCoordinator(coordinator);
            i.setAssesmentPeriod(coordinator.getAssesmentPeriod());

            List<ResearchSeedbed> researchSeedbeds = new ArrayList<>();
            i.setResearchSeedbeds(researchSeedbeds);

            repositoryInvestigationGroup.save(i);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Transactional
    public Boolean addInvestigationGroupsByExcel(MultipartFile file, Long assessmentPeriodId){
        try{
            List<Map<String, String>> listOfMaps = serviceUpload.uploadExcel(file);

            if (listOfMaps == null || listOfMaps.isEmpty()) {
                return false;
            }

            //First, we create the functionary profiles
            for(Map<String, String> map : listOfMaps){
                HashMap<String, String> functionaryProfile = new HashMap<>();
                functionaryProfile.put("identification_number", map.get("coordinator_id"));
                functionaryProfile.put("assesment_period_id", assessmentPeriodId.toString());
                serviceFunctionaryProfile.addFunctionaryProfile(functionaryProfile);
            }

            //Then, we create the investigation groups
            for(Map<String, String> map : listOfMaps){
                HashMap<String, String> investigationGroup = new HashMap<>();
                investigationGroup.put("name", map.get("name"));
                investigationGroup.put("coordinator_fp_id", repositoryFunctionaryProfile.findByIdentificationNumber(map.get("coordinator_id")).get().getId().toString());
                addInvestigationGroup(investigationGroup);
            }
            return true;

        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Method to update the coordinator of an investigation group, it receives a map with the following keys:
     * coordinator_fp_id, investigation_group_id
     * @param map JSON with the keys mentioned above
     * @return true if the coordinator was updated successfully, false otherwise
     */
    public Boolean updateInvestigationGroupCoordinator(HashMap<String, String> map){
        try{
            Long coordinatorId = Long.parseLong(map.get("coordinator_fp_id"));
            Long investigationGroupId = Long.parseLong(map.get("investigation_group_id"));

            if(repositoryFunctionaryProfile.findById(coordinatorId).isEmpty() || repositoryInvestigationGroup.findById(investigationGroupId).isEmpty()){
                return false;
            }

            InvestigationGroup i = repositoryInvestigationGroup.findById(investigationGroupId).get();
            FunctionaryProfile coordinator = repositoryFunctionaryProfile.findById(coordinatorId).get();

            boolean sameAssessmentPeriod = !i.getAssesmentPeriod().getId().equals(coordinator.getAssesmentPeriod().getId());
            boolean isTheSameCoordinator = i.getCoordinator().getId().equals(coordinator.getId());

            if(sameAssessmentPeriod || isTheSameCoordinator){
                return false;
            }

            i.setCoordinator(coordinator);
            repositoryInvestigationGroup.save(i);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Method to update the name of an investigation group, it receives a map with the following keys:
     * investigation_group_id, new_name
     * @param map JSON with the keys mentioned above
     * @return true if the name was updated successfully, false otherwise
     */
    public Boolean updateInvestigationGroupName(HashMap<String, String> map){
        try{
            Long investigationGroupId = Long.parseLong(map.get("investigation_group_id"));
            String name = map.get("new_name");

            if(repositoryInvestigationGroup.findById(investigationGroupId).isEmpty()){
                return false;
            }

            InvestigationGroup i = repositoryInvestigationGroup.findById(investigationGroupId).get();

            List<InvestigationGroup> investigationGroups = repositoryInvestigationGroup.findByAssesmentPeriodId(i.getAssesmentPeriod().getId());
            for(InvestigationGroup investigationGroup : investigationGroups){
                if(investigationGroup.getName().equalsIgnoreCase(name)){
                    return false;
                }
            }

            i.setName(name);
            repositoryInvestigationGroup.save(i);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    @Transactional
    public Boolean deleteInvestigationGroup(Long investigationGroupId) {
        try{
            if(repositoryInvestigationGroup.findById(investigationGroupId).isPresent()){
                repositoryInvestigationGroup.deleteResearchSeedbed(investigationGroupId);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
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
