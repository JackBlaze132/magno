package com.unibague.backend.controller;

import com.unibague.backend.model.User;
import com.unibague.backend.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerUser {

    @Autowired
    ServiceUser serviceUser;


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
        String url = "http://integra.unibague.edu.co/studentInfo?api_token=$2y$42$s/9xFMDieYOEvYD/gfPqFAeFzvWXt13feXyterJzQ9rZKrbLpBYUqo&code_user=" + String.valueOf(id) +"&type=C";
        return serviceUser.fetchExternalData(url);
    }
}
