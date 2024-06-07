package com.unibague.backend.controller;

import com.unibague.backend.model.StudentProfile;
import com.unibague.backend.service.ServiceStudentProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class ControllerStudentProfile {

    @Autowired
    ServiceStudentProfile serviceStudentProfile;

    @GetMapping(path = "/getStudentProfiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentProfile> getStudentProfiles() {
        return  serviceStudentProfile.getStudentProfiles();
    }

    @GetMapping(path = "/getStudentProfiles1", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentProfile> getStudentProfiles1() {
        return  serviceStudentProfile.getStudentProfiles1();
    }

    @GetMapping(path = "/getStudentProfiles2", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentProfile> getStudentProfiles2() {
        return  serviceStudentProfile.getStudentProfiles2();
    }

    @PostMapping(path = "/addStudentProfile", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Boolean addStudentProfile(@RequestBody HashMap<String, String> studentProfile) {
        return serviceStudentProfile.addStudentProfile(studentProfile);
    }
}
