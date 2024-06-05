package com.unibague.backend.service;

import com.unibague.backend.model.AssesmentPeriod;
import com.unibague.backend.repository.RepositoryAssesmentPeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAssesmentPeriod {

    @Autowired
    private RepositoryAssesmentPeriod repositoryAssesmentPeriod;

    public boolean addAssesmentPeriod(AssesmentPeriod assesmentPeriod) {
        try{
            System.out.println(assesmentPeriod.toString());
            repositoryAssesmentPeriod.save(assesmentPeriod);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
