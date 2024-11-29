package com.unibague.backend.service;

import com.unibague.backend.model.AssesmentPeriod;
import com.unibague.backend.repository.RepositoryAssesmentPeriod;
import com.unibague.backend.util.ExceptionLogger;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            ExceptionLogger.logException(e);
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
            ExceptionLogger.logException(e);
            System.out.println("Error deleting assessment period " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     *This method updates the name, the dates or both of an assessment period, notice that there are three possible types of update
     * and use the following keys: id, name, start_date, end_date
     * @param map JSON with the keys and values to update
     * @return true if the update was successful, false otherwise
     */
    public Boolean updateAssessmentPeriod(Map<String, String> map) {
        String id = map.get("id");
        Long idLong = Long.parseLong(id);

        if (repositoryAssesmentPeriod.findById(idLong).isEmpty()) {
            return false;
        }

        AssesmentPeriod assessmentPeriod = repositoryAssesmentPeriod.findById(idLong).get();

        if (map.containsKey("name") && !map.get("name").isEmpty()) {
            assessmentPeriod.setName(map.get("name"));
        }

        if (map.containsKey("start_date") && !map.get("start_date").isEmpty()) {
            assessmentPeriod.setStartDate(Date.valueOf(map.get("start_date").substring(0, 10)));
        }

        if (map.containsKey("end_date") && !map.get("end_date").isEmpty()) {
            assessmentPeriod.setEndDate(Date.valueOf(map.get("end_date").substring(0, 10)));
        }

        try {
            repositoryAssesmentPeriod.save(assessmentPeriod);
            return true;
        } catch (Exception e) {
            ExceptionLogger.logException(e);
            System.out.println("Error updating assessment period " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
}