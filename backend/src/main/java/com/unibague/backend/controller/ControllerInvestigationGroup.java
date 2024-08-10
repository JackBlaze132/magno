package com.unibague.backend.controller;

import com.unibague.backend.model.InvestigationGroup;
import com.unibague.backend.service.ServiceInvestigationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@RestController
public class ControllerInvestigationGroup {

    @Autowired
    ServiceInvestigationGroup serviceInvestigationGroup;

    @GetMapping("/getInvestigationGroups")
    public List<InvestigationGroup> getInvestigationGroups() {
        return serviceInvestigationGroup.getInvestigationGroups();
    }

    @PostMapping(path = "/addInvestigationGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean addInvestigationGroup(@RequestBody HashMap<String, String> investigationGroup) {
        return serviceInvestigationGroup.addInvestigationGroup(investigationGroup);
    }

    @PostMapping(path = "/addInvestigationGroupsByExcel/{assessmentPeriodId}")
    public boolean addInvestigationGroupsByExcel(@PathVariable Long assessmentPeriodId, @RequestParam("file") MultipartFile file) {
        return serviceInvestigationGroup.addInvestigationGroupsByExcel(file, assessmentPeriodId);
    }

    @PatchMapping(path = "/updateInvestigationGroupCoordinator", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateInvestigationGroupCoordinator(@RequestBody HashMap<String, String> map) {
        return serviceInvestigationGroup.updateInvestigationGroupCoordinator(map);
    }

    @GetMapping("/getInvestigationGroupsByAssesmentPeriodId/{id}")
    public List<InvestigationGroup> getInvestigationGroupsByAssesmentPeriodId(@PathVariable Long id) {
        return serviceInvestigationGroup.findInvestigationGroupByAssesmentPeriod(id);
    }

    @GetMapping("/getAllInvestigationGroupsOrderedByAssesmentPeriod")
    public List<InvestigationGroup> getAllInvestigationGroupsOrderedByAssesmentPeriod() {
        return serviceInvestigationGroup.findAllOrderedByAssesmentPeriod();
    }

    @PatchMapping(path = "/updateInvestigationGroupName", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateInvestigationGroupName(@RequestBody HashMap<String, String> map) {
        return serviceInvestigationGroup.updateInvestigationGroupName(map);
    }

    @DeleteMapping("/deleteInvestigationGroup/{investigationGroupId}")
    public boolean deleteInvestigationGroup(@PathVariable Long investigationGroupId) {
        return serviceInvestigationGroup.deleteInvestigationGroup(investigationGroupId);
    }
}
