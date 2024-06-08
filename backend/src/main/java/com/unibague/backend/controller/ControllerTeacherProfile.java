package com.unibague.backend.controller;

import com.unibague.backend.model.FunctionaryProfile;
import com.unibague.backend.service.ServiceTeacherProfile;
import com.unibague.backend.util.FetchExternalData;
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
    ServiceTeacherProfile serviceTeacherProfile;

    @GetMapping("/getTeacherProfiles")
    public List<FunctionaryProfile> getTeacherProfiles() {
        return serviceTeacherProfile.getTeacherProfiles();
    }

    @GetMapping("/getTeacherProfiles1")
    public List<FunctionaryProfile> getTeacherProfiles1() {
        return serviceTeacherProfile.getTeacherProfiles1();
    }

    @GetMapping("/getTeacherProfiles2")
    public List<FunctionaryProfile> getTeacherProfiles2() {
        return serviceTeacherProfile.getTeacherProfiles2();
    }

    @GetMapping("/getResearchSeedbedCoordinator1")
    public FunctionaryProfile getResearchSeedbedCoordinator1() {
        return serviceTeacherProfile.findResearchSeedbedCoordinator1();
    }

    @GetMapping("/getResearchSeedbedCoordinator2")
    public FunctionaryProfile getResearchSeedbedCoordinator2() {
        return serviceTeacherProfile.findResearchSeedbedCoordinator2();
    }

    @GetMapping("/getInvestigationGroupDirector1")
    public FunctionaryProfile getInvestigationGroupDirector1() {
        return serviceTeacherProfile.findInvestigationGroupDirector1();
    }

    @PostMapping("/addTeacherProfile")
    public Boolean addTeacherProfile(@RequestBody HashMap<String, String> functionaryProfile) {
        return serviceTeacherProfile.addTeacherProfile(functionaryProfile);
    }
}
