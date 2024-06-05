package com.unibague.backend.controller;

import com.unibague.backend.model.AssesmentPeriod;
import com.unibague.backend.service.ServiceAssesmentPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class ControllerAssesmentPeriod {

    @Autowired
    ServiceAssesmentPeriod serviceAssesmentPeriod;

    @PostMapping(path = "/addAssesmentPeriod", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean addAssesmentPeriod(@RequestBody HashMap<String, String> assesmentPeriod) {
        return serviceAssesmentPeriod.addAssesmentPeriod(assesmentPeriod);
    }
}
