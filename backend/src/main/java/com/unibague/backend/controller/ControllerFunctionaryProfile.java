package com.unibague.backend.controller;

import com.unibague.backend.model.FunctionaryProfile;
import com.unibague.backend.service.ServiceFunctionaryProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class ControllerFunctionaryProfile {

    @Autowired
    ServiceFunctionaryProfile serviceFunctionaryProfile;

    @GetMapping("/getFunctionaryProfiles")
    public List<FunctionaryProfile> getTeacherProfiles() {
        return serviceFunctionaryProfile.getFunctionaryProfiles();
    }

    @PostMapping("/addFunctionaryProfile")
    public Boolean addFunctionaryProfile(@RequestBody HashMap<String, String> functionaryProfile) {
        return serviceFunctionaryProfile.addFunctionaryProfile(functionaryProfile);
    }

    @PostMapping("/addFunctionaryProfileToAResearchSeedbed")
    public Boolean addFunctionaryProfileToAResearchSeedbed(@RequestBody HashMap<String, String> functionaryProfile) {
        return serviceFunctionaryProfile.addFunctionaryProfileToAResearchSeedbed(functionaryProfile);
    }

    @GetMapping(path = "/getTutorByResearchseedbedId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FunctionaryProfile getTutorByResearchseedbedId(@PathVariable Long id) {
        return serviceFunctionaryProfile.getTutorByResearchSeedbedId(id);
    }

    @GetMapping(path = "/getCoordinatorByResearchseedbedId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FunctionaryProfile getCoordinatorByResearchseedbedId(@PathVariable Long id) {
        return serviceFunctionaryProfile.getCoordinatorByResearchSeedbedId(id);
    }

    @GetMapping(path = "/getExternalFunctionaryProfilesByResearchSeedbedId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FunctionaryProfile> getExternalFunctionaryProfilesByResearchSeedbedId(@PathVariable Long id) {
        return serviceFunctionaryProfile.getExternalFunctionaryProfilesByResearchSeedbedId(id);
    }

    @GetMapping(path = "/getFunctionaryProfileByAssesmentPeriodId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FunctionaryProfile> getFunctionaryProfilesByAssesmentPeriodId(@PathVariable Long id) {
        return serviceFunctionaryProfile.getFunctionaryProfilesByAssesmentPeriod(id);
    }
}
