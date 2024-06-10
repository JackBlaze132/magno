package com.unibague.backend.controller;

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

    @PostMapping(path = "/addStudentProfileByExcel/{apid}/{rsid}")
    //apid = assesment_period_id / rsid = research_seedbed_id
    public Boolean addUserByExcel(@RequestParam("file") MultipartFile file, @PathVariable String apid, @PathVariable String rsid) {
        List<Map<String, String>> retorno = null;
        try{
            retorno = serviceUpload.uploadExcel(file);
        }catch (Exception e) {
            System.out.println("Error: ");
            e.printStackTrace();
            return false;
        }
        return serviceStudentProfile.addStudentProfilesByExcel(retorno, apid, rsid);
    }
}
