package com.unibague.backend.service;

import com.unibague.backend.model.User;
import com.unibague.backend.repository.RepositoryUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceUser {

    @Autowired
    private RepositoryUser repositoryUser;

    @Autowired
    private RestTemplate restTemplate;

    public Boolean addStudent(User user) {
        try{
            repositoryUser.save(user);
            return true;
        } catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getStudents() {
        return repositoryUser.findAll();
    }

    public String fetchExternalData(String url) {
        return restTemplate.getForObject(url, String.class);
    }

    @Transactional
    public  Boolean addNewUsers(List<User> users){
        if(users == null || users.isEmpty()){
            return false;
        }
        try {
            for (User user : users) {
                Optional<User> existingUserOpt = repositoryUser.findByUserIdentification(user.getUserIdentification());
                if(existingUserOpt.isEmpty()){
                    repositoryUser.save(user);
                }
                else {
                    if(user.getEmail().equals(existingUserOpt.get().getEmail()) && user.getIsExternalUser().equals(existingUserOpt.get().getIsExternalUser())){
                        continue;
                    }
                    repositoryUser.updateUserByUserIdentification(user.getEmail(), user.getIsExternalUser(), user.getUserIdentification());
                    repositoryUser.flush();
                }
            }
            repositoryUser.deleteUserWithEmptyEmailOrUserIdentification();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
