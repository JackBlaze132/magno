package com.unibague.backend.controller;

import com.unibague.backend.model.AssesmentPeriod;
import com.unibague.backend.service.ServiceAssesmentPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class ControllerAssesmentPeriod {

    @Autowired
    ServiceAssesmentPeriod serviceAssesmentPeriod;

    @GetMapping(path = "/getAssesmentPeriods", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AssesmentPeriod> getAssesmentPeriods() {
        return serviceAssesmentPeriod.getAssesmentPeriods();
    }

    @PostMapping(path = "/addAssesmentPeriod", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean addAssesmentPeriod(@RequestBody HashMap<String, String> assesmentPeriod) {
        return serviceAssesmentPeriod.addAssessmentPeriod(assesmentPeriod);
    }
}
