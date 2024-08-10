package com.unibague.backend.service;

import com.unibague.backend.model.AssesmentPeriod;
import com.unibague.backend.repository.RepositoryAssesmentPeriod;
import jakarta.transaction.Transactional;
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

    public boolean addAssessmentPeriod(HashMap<String, String> assessmentPeriod) {
        try {
            AssesmentPeriod a = new AssesmentPeriod();
            // Notice that the substring method is used because the date values that come from the frontend
            // Have the format  (example) "2024-07-03T05:00:00.000Z"
            // If this method fails, the date format should be checked
            a.setStartDate(Date.valueOf(assessmentPeriod.get("start_date").substring(0,10)));
            a.setEndDate(Date.valueOf(assessmentPeriod.get("end_date").substring(0,10)));
            a.setName(assessmentPeriod.get("name"));
            a.setIsActive(Boolean.parseBoolean(assessmentPeriod.get("is_active")));
            repositoryAssesmentPeriod.save(a);

            return true;    
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public Boolean deleteAssessmentPeriod(Long assessmentPeriodId){
        try {
            if(repositoryAssesmentPeriod.findById(assessmentPeriodId).isPresent()){
                repositoryAssesmentPeriod.deleteAssessmentPeriod(assessmentPeriodId);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error deleting assessment period " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}