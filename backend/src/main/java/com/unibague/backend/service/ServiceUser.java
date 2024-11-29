package com.unibague.backend.service;

import com.unibague.backend.model.User;
import com.unibague.backend.repository.RepositoryUser;
import com.unibague.backend.util.ExceptionLogger;
import com.unibague.backend.util.IntegraStudentNomenclature;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ServiceUser {

    @Autowired
    private RepositoryUser repositoryUser;

    @Autowired
    private RestTemplate restTemplate;

    public Boolean addUser(HashMap<String, String> user) {
        try{
            User u = new User();
            u.setEmail(user.get("email"));
            u.setIsExternalUser(Boolean.parseBoolean(user.get("isExternalUser")));
            u.setUserIdentification(user.get("identification"));
            repositoryUser.saveAndFlush(u);
            return true;
        } catch (Exception e) {
            ExceptionLogger.logException(e);
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

    /*@Transactional
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
    }*/

    @Transactional
    public  Boolean addNewUsers(List<Map<String, String>> listOfMaps){

        if(listOfMaps == null || listOfMaps.isEmpty()){
            return false;
        }

        List<User> users = new ArrayList<User>();
        for (Map<String, String> stringStringMap : listOfMaps) {

            String userIdentification = stringStringMap.get(IntegraStudentNomenclature.IDENTIFICATION);
            String email = stringStringMap.get(IntegraStudentNomenclature.EMAIL);
            //If the Excel file doesn't have the is_external column, the default value is false
            Boolean isExternalUser = stringStringMap.containsKey("is_external") && Boolean.parseBoolean(stringStringMap.get("is_external"));

            User user = new User(userIdentification, email, isExternalUser);
            users.add(user);
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
            ExceptionLogger.logException(e);
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
