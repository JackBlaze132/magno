package com.unibague.backend.service;

import com.unibague.backend.model.AcademicProgram;
import com.unibague.backend.repository.RepositoryAcademicProgram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ServiceAcademicProgram {

    @Autowired
    private RepositoryAcademicProgram repositoryAcademicProgram;

    public List<AcademicProgram> getAcademicPrograms(){
        return repositoryAcademicProgram.findAll();
    }

    public boolean addAcademicProgram(HashMap<String, String> academicProgram) {
        try {

            AcademicProgram a = new AcademicProgram();
            a.setName(academicProgram.get("name"));
            repositoryAcademicProgram.save(a);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
