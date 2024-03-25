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
}
