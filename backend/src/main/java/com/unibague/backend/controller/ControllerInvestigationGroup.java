package com.unibague.backend.controller;

import com.unibague.backend.model.InvestigationGroup;
import com.unibague.backend.service.ServiceInvestigationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getInvestigationGroupById/{id}")
    public List<InvestigationGroup> getInvestigationGroupById(@PathVariable Long id) {
        return serviceInvestigationGroup.findInvestigationGroupByAssesmentPeriod(id);
    }
}
