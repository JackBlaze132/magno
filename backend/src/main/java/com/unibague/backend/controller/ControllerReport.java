package com.unibague.backend.controller;

import com.unibague.backend.service.ServiceReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerReport {

    @Autowired
    ServiceReport serviceReport;

    @GetMapping("/report/{id}")
    public String getReport(@PathVariable Long id){
        return serviceReport.generateReport(id);
    }
}
