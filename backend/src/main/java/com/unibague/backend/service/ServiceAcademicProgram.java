package com.unibague.backend.service;

import com.unibague.backend.model.AcademicProgram;
import com.unibague.backend.repository.RepositoryAcademicProgram;
import com.unibague.backend.util.ExceptionLogger;
import com.unibague.backend.util.FetchExternalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ServiceAcademicProgram {

    @Autowired
    private RepositoryAcademicProgram repositoryAcademicProgram;

    public List<AcademicProgram> getAcademicPrograms() {
        return repositoryAcademicProgram.findAll();
    }

    public boolean addAcademicProgram(HashMap<String, String> academicProgram) {
        try {

            AcademicProgram a = new AcademicProgram();
            a.setName(academicProgram.get("name"));
            a.setProgramCode(academicProgram.get("program_code"));
            repositoryAcademicProgram.save(a);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionLogger.logException(e);
            return false;
        }
    }

    /**
     * Notice that we are checking if the academic program already exists in the database only by the program code
     * If some program code are renamed, but the program code is the same, the program will not be added again
     */
    public boolean addAllAcademicPrograms() {
        try {
            List<AcademicProgram> academicPrograms = FetchExternalData.fetchAcademicProgramNamesFromExternalData();
            for (AcademicProgram academicProgram : academicPrograms) {
                if (!repositoryAcademicProgram.existsByProgramCode(academicProgram.getProgramCode())) {
                    repositoryAcademicProgram.save(academicProgram);
                }
            }
            return true;
        } catch (Exception e) {
            ExceptionLogger.logException(e);
            e.printStackTrace();
            return false;
        }
    }
}
