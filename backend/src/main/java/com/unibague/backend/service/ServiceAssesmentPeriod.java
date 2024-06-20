package com.unibague.backend.service;

import com.unibague.backend.model.AssesmentPeriod;
import com.unibague.backend.repository.RepositoryAssesmentPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
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
        try {
            AssesmentPeriod a = new AssesmentPeriod();

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

            Date startDate = (Date) sdf.parse(assesmentPeriod.get("start_date"));
            a.setStartDate(startDate);

            Date endDate = (Date) sdf.parse(assesmentPeriod.get("end_date"));
            a.setEndDate(endDate);

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
