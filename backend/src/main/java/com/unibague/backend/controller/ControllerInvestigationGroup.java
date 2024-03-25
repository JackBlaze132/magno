package com.unibague.backend.controller;

import com.unibague.backend.model.InvestigationGroup;
import com.unibague.backend.service.ServiceInvestigationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerInvestigationGroup {

    @Autowired
    ServiceInvestigationGroup serviceInvestigationGroup;

    @GetMapping("/getInvestigationGroups")
    public List<InvestigationGroup> getInvestigationGroups() {
        return serviceInvestigationGroup.getInvestigationGroups();
    }
}
