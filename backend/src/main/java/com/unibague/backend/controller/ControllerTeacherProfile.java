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

    @PostMapping("/addTeacherProfile")
    public Boolean addTeacherProfile(@RequestBody TeacherProfile teacherProfile) {
        return serviceTeacherProfile.addTeacherProfile(teacherProfile);
    }
}
