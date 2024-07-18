package com.unibague.backend.controller;

import com.unibague.backend.model.ResearchSeedbed;
import com.unibague.backend.service.ServiceResearchSeedbed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class ControllerResearchSeedbed {

    @Autowired
    ServiceResearchSeedbed serviceResearchSeedbed;

    @GetMapping("/getResearchSeedbeds")
    public List<ResearchSeedbed> getResearchSeedbeds(){
        return serviceResearchSeedbed.getResearchSeedbeds();
    }

    @PostMapping("/addResearchSeedbed")
    public Boolean addResearchSeedbed(@RequestBody HashMap<String, String> researchSeedbed){
        return serviceResearchSeedbed.addResearchSeedbed(researchSeedbed);
    }

    @GetMapping("/getResearchSeedbedsByInvestigationGroupId/{id}")
    public List<ResearchSeedbed> getResearchSeedbedsByInvestigationGroupId(@PathVariable Long id){
        return serviceResearchSeedbed.getResearchSeedbedsByInvestigationGroupId(id);
    }

    @GetMapping("/getResearchSeedbedById/{id}")
    public ResearchSeedbed getResearchSeedbedById(@PathVariable Long id){
        return serviceResearchSeedbed.getResearchSeedbedById(id);
    }
}
