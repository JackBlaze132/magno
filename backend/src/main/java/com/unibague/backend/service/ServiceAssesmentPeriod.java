package com.unibague.backend.service;

import com.unibague.backend.model.AssesmentPeriod;
import com.unibague.backend.repository.RepositoryAssesmentPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ServiceAssesmentPeriod {

    @Autowired
    private RepositoryAssesmentPeriod repositoryAssesmentPeriod;

    public List<AssesmentPeriod> getAssesmentPeriods() {
        return repositoryAssesmentPeriod.findAll();
    }

    public boolean addAssesmentPeriod(HashMap<String, String> assesmentPeriod) {
        try{

            AssesmentPeriod a = new AssesmentPeriod();
            a.setStartDate(Date.valueOf(assesmentPeriod.get("start_date")));
            a.setEndDate(Date.valueOf(assesmentPeriod.get("end_date")));
            a.setName(assesmentPeriod.get("name"));
            a.setIsActive(Boolean.parseBoolean(assesmentPeriod.get("is_active")));
            repositoryAssesmentPeriod.save(a);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
