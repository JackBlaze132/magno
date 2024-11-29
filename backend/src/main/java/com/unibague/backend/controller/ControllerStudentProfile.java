package com.unibague.backend.controller;

import com.unibague.backend.model.ResearchSeedbed;
import com.unibague.backend.model.StudentProfile;
import com.unibague.backend.model.User;
import com.unibague.backend.service.ServiceStudentProfile;
import com.unibague.backend.service.ServiceUpload;
import com.unibague.backend.util.IntegraStudentNomenclature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ControllerStudentProfile {

    @Autowired
    ServiceStudentProfile serviceStudentProfile;

    private final ServiceUpload serviceUpload;

    public ControllerStudentProfile(ServiceUpload serviceUpload) {
        this.serviceUpload = serviceUpload;
    }

    @GetMapping(path = "/getStudentProfiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentProfile> getStudentProfiles() {
        return  serviceStudentProfile.getStudentProfiles();
    }

    @PostMapping(path = "/addStudentProfile", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Boolean addStudentProfile(@RequestBody HashMap<String, String> studentProfile) {
        return serviceStudentProfile.addStudentProfileToAResearchSeedbed(studentProfile);
    }

    @GetMapping(path = "/getStudentProfilesResearchSeedbed", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ResearchSeedbed> getStudentProfilesResearchSeedbed(@RequestBody String identification) {
        return serviceStudentProfile.getStudentProfileResearchSeedbeds(identification);
    }

    @PostMapping(path = "/addStudentProfileByExcel/{researchSeedbedId}")
    public Boolean addUserByExcel(@RequestParam("file") MultipartFile file, @PathVariable String researchSeedbedId) {
        return serviceStudentProfile.addStudentsProfilesByExcel(file, researchSeedbedId);
    }

    @GetMapping(path = "/getStudentProfilesByResearchSeedbedId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentProfile> getStudentProfilesByResearchSeedbedId(@PathVariable Long id) {
        return serviceStudentProfile.getStudentProfilesByResearchSeedbedId(id);
    }

    @GetMapping(path = "/getStudentProfilesByUserIdentification/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentProfile> getStudentProfilesByUserIdentification(@PathVariable Long id) {
        return serviceStudentProfile.getStudentProfilesByUserIdentification(id);
    }

    @DeleteMapping(path = "/deleteStudentProfileFromAResearchSeedbed", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Boolean deleteStudentProfileFromAResearchSeedbed(@RequestBody HashMap<String, String> map) {
        return serviceStudentProfile.deleteStudentProfileFromResearchSeedbed(map);
    }
}
