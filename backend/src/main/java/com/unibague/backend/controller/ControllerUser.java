package com.unibague.backend.controller;

import com.unibague.backend.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControllerUser {

    @Autowired
    ServiceUser serviceStudent;


    @RequestMapping(path="/")
    public String hello_world() {
        return "Hello World!";
    }
}
