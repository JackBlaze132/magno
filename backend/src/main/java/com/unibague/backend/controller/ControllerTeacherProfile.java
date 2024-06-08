package com.unibague.backend.controller;

import com.unibague.backend.model.FunctionaryProfile;
import com.unibague.backend.service.ServiceFunctionaryProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class ControllerTeacherProfile {

    @Autowired
    ServiceFunctionaryProfile serviceFunctionaryProfile;

    @GetMapping("/getTeacherProfiles")
    public List<FunctionaryProfile> getTeacherProfiles() {
        return serviceFunctionaryProfile.getTeacherProfiles();
    }

    @GetMapping("/getTeacherProfiles1")
    public List<FunctionaryProfile> getTeacherProfiles1() {
        return serviceFunctionaryProfile.getTeacherProfiles1();
    }

    @GetMapping("/getTeacherProfiles2")
    public List<FunctionaryProfile> getTeacherProfiles2() {
        return serviceFunctionaryProfile.getTeacherProfiles2();
    }

    @GetMapping("/getResearchSeedbedCoordinator1")
    public FunctionaryProfile getResearchSeedbedCoordinator1() {
        return serviceFunctionaryProfile.findResearchSeedbedCoordinator1();
    }

    @GetMapping("/getResearchSeedbedCoordinator2")
    public FunctionaryProfile getResearchSeedbedCoordinator2() {
        return serviceFunctionaryProfile.findResearchSeedbedCoordinator2();
    }

    @GetMapping("/getInvestigationGroupDirector1")
    public FunctionaryProfile getInvestigationGroupDirector1() {
        return serviceFunctionaryProfile.findInvestigationGroupDirector1();
    }

    @PostMapping("/addTeacherProfile")
    public Boolean addTeacherProfile(@RequestBody HashMap<String, String> functionaryProfile) {
        return serviceFunctionaryProfile.addTeacherProfile(functionaryProfile);
    }
}
