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

    @GetMapping("/getResearchSeedbeds1")
    public List<ResearchSeedbed> getResearchSeedbeds1(){
        return serviceResearchSeedbed.getResearchSeedbeds1();
    }

    @PostMapping("/addResearchSeedbed")
    public Boolean addResearchSeedbed(@RequestBody HashMap<String, String> researchSeedbed){
        return serviceResearchSeedbed.addResearchSeedbed(researchSeedbed);
    }

    @GetMapping("/getResearchSeedbedsById/{id}")
    public List<ResearchSeedbed> getResearchSeedbedsById(@PathVariable Long id){
        return serviceResearchSeedbed.getResearchSeedbedsByInvestigationGroup(id);
    }
}
