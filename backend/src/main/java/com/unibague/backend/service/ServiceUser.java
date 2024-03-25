package com.unibague.backend.service;

import com.unibague.backend.model.User;
import com.unibague.backend.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUser {

    @Autowired
    private RepositoryUser repositoryUser;

    public Boolean addStudent(User user) {
        try{
            repositoryUser.save(user);
            return true;
        } catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
            return false;
        }
    }

    public User getStudentById(Long id) {
        return repositoryUser.findById(id).orElse(null);
    }

    public List<User> getStudents() {
        return (List<User>) repositoryUser.findAll();
    }
}
