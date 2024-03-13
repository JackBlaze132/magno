package com.unibague.backend.controller;

import com.unibague.backend.model.User;
import com.unibague.backend.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerUser {

    @Autowired
    ServiceUser serviceStudent;


    @RequestMapping(path="/getUsers")
    public List<User> hello_world() {
        return  serviceStudent.getStudents();
    }
}
