package com.unibague.backend.controller;

import com.unibague.backend.model.AcademicProgram;
import com.unibague.backend.service.ServiceAcademicProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerAcademicProgram {

    @Autowired
    private ServiceAcademicProgram serviceAcademicProgram;

    @GetMapping("/getAcademicPrograms")
    public List<AcademicProgram> getAcademicPrograms(){
        return serviceAcademicProgram.getAcademicPrograms();
    }
}
