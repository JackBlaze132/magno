package com.unibague.backend.controller;

import com.unibague.backend.model.User;
import com.unibague.backend.repository.RepositoryUser;
import com.unibague.backend.service.ServiceUpload;
import com.unibague.backend.service.ServiceUser;
import com.unibague.backend.util.FetchExternalData;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ControllerUser {

    @Autowired
    ServiceUser serviceUser;

    @Autowired
    RepositoryUser repositoryUser;

    private final ServiceUpload serviceUpload;

    public ControllerUser(ServiceUpload serviceUpload) {
        this.serviceUpload = serviceUpload;
    }


    @GetMapping(path = "/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        return  serviceUser.getStudents();
    }

    @PostMapping(path = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Boolean addUser(@RequestBody User user) {
        return serviceUser.addStudent(user);
    }

    @GetMapping(path = "/getUserst/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUserData(@PathVariable Long id) {
        return serviceUser.fetchExternalData(FetchExternalData.fetchExternalDataFromStudent(String.valueOf(id)));
    }

    @PostMapping(path = "/addUsersByExcel")
    public Boolean addUserByExcel(@RequestParam("file") MultipartFile file) {
        List<Map<String, String>> retorno = null;
        try{
            retorno = serviceUpload.uploadExcel(file);
        }catch (Exception e) {
            retorno = null;
            System.out.println("Error: ");
            e.printStackTrace();
            return false;
        }

        List<User> users = new ArrayList<User>();
        for (Map<String, String> stringStringMap : retorno) {

            String userCode = stringStringMap.get("user_code");
            String email = stringStringMap.get("email");
            Boolean isExternalUser = Boolean.parseBoolean(stringStringMap.get("is_external_user"));

            User user = new User(userCode, email, isExternalUser);
            users.add(user);
        }
        return serviceUser.addNewUsers(users);
    }

}
