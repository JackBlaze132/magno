package com.unibague.backend.service;

import com.unibague.backend.model.AcademicProgram;
import com.unibague.backend.repository.RepositoryAcademicProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAcademicProgram {

    @Autowired
    private RepositoryAcademicProgram repositoryAcademicProgram;

    public List<AcademicProgram> getAcademicPrograms(){
        return repositoryAcademicProgram.findAll();
    }

    public boolean addAcademicProgram(AcademicProgram academicProgram) {
        try {
            repositoryAcademicProgram.save(academicProgram);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
