package com.unibague.backend.controller;

import com.unibague.backend.model.TeacherProfile;
import com.unibague.backend.service.ServiceTeacherProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerTeacherProfile {

    @Autowired
    ServiceTeacherProfile serviceTeacherProfile;

    @GetMapping("/getTeacherProfiles")
    public List<TeacherProfile> getTeacherProfiles() {
        return serviceTeacherProfile.getTeacherProfiles();
    }

    @GetMapping("/getTeacherProfiles1")
    public List<TeacherProfile> getTeacherProfiles1() {
        return serviceTeacherProfile.getTeacherProfiles1();
    }

    @GetMapping("/getTeacherProfiles2")
    public List<TeacherProfile> getTeacherProfiles2() {
        return serviceTeacherProfile.getTeacherProfiles2();
    }

    @GetMapping("/getResearchSeedbedCoordinator1")
    public TeacherProfile getResearchSeedbedCoordinator1() {
        return serviceTeacherProfile.findResearchSeedbedCoordinator1();
    }

    @GetMapping("/getResearchSeedbedCoordinator2")
    public TeacherProfile getResearchSeedbedCoordinator2() {
        return serviceTeacherProfile.findResearchSeedbedCoordinator2();
    }

    @GetMapping("/getInvestigationGroupDirector1")
    public TeacherProfile getInvestigationGroupDirector1() {
        return serviceTeacherProfile.findInvestigationGroupDirector1();
    }

    @PostMapping("/addTeacherProfile")
    public Boolean addTeacherProfile(@RequestBody TeacherProfile teacherProfile) {
        return serviceTeacherProfile.addTeacherProfile(teacherProfile);
    }
}
