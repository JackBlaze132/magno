package com.unibague.backend.controller;

import com.unibague.backend.model.AcademicProgram;
import com.unibague.backend.service.ServiceAcademicProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class ControllerAcademicProgram {

    @Autowired
    private ServiceAcademicProgram serviceAcademicProgram;

    @GetMapping("/getAcademicPrograms")
    public List<AcademicProgram> getAcademicPrograms(){
        return serviceAcademicProgram.getAcademicPrograms();
    }

    @PostMapping(path = "/addAcademicProgram", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean addAcademicProgram(@RequestBody HashMap<String, String> academicProgram){
        return serviceAcademicProgram.addAcademicProgram(academicProgram);
    }
}
