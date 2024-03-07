package com.unibague.backend.controller;

import com.unibague.backend.model.Student;
import com.unibague.backend.service.ServiceStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControllerStudent {

    @Autowired
    ServiceStudent serviceStudent;


    @RequestMapping(path="/")
    public String hello_world() {
        return "Hello World!";
    }
}
