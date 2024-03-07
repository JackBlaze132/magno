package com.unibague.backend.service;

import com.unibague.backend.model.Student;
import com.unibague.backend.repository.RepositoryStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceStudent {

    @Autowired
    private RepositoryStudent repositoryStudent;

    public Boolean addStudent(Student student) {
        try{
            repositoryStudent.save(student);
            return true;
        } catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
            return false;
        }
    }

    public Student getStudentById(Long id) {
        return repositoryStudent.findById(id).orElse(null);
    }

    public List<Student> getStudents() {
        return (List<Student>) repositoryStudent.findAll();
    }
}
