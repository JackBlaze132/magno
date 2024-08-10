package com.unibague.backend.service;

import com.unibague.backend.model.AssesmentPeriod;
import com.unibague.backend.model.InvestigationGroup;
import com.unibague.backend.repository.RepositoryAssesmentPeriod;
import com.unibague.backend.repository.RepositoryFunctionaryProfile;
import com.unibague.backend.repository.RepositoryStudentProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceReport {

    @Autowired
    RepositoryAssesmentPeriod repositoryAssesmentPeriod;

    @Autowired
    RepositoryStudentProfile repositoryStudentProfile;

    @Autowired
    RepositoryFunctionaryProfile repositoryFunctionaryProfile;
    public String generateReport(Long assessmentPeriodId){
        AssesmentPeriod assesmentPeriod;
        if(repositoryAssesmentPeriod.findById(assessmentPeriodId).isEmpty()){
            return "";
        }
        assesmentPeriod = repositoryAssesmentPeriod.findById(assessmentPeriodId).get();
        String report = "Reporte de Semilleros de Investigación periodo academico: " + assesmentPeriod.getName();
        report += "\n Numero de grupos de investigación: " + assesmentPeriod.getInvestigationGroups().size();

        int count = 0;
        for(InvestigationGroup ig : assesmentPeriod.getInvestigationGroups()){
            count += ig.getResearchSeedbeds().size();
        }
        report += "\n Numero de semilleros de investigación: " + count;
        report += "\n Numero de estudiantes en semilleros de investigación: " + repositoryStudentProfile.countStudentsInAnAssessmentPeriod(assesmentPeriod);
        report += "\n Numero de docentes en semilleros de investigación: " + repositoryFunctionaryProfile.countFunctionariesInAnAssessmentPeriod(assesmentPeriod);
        return report;
    }
}

