package com.unibague.backend.controller;

import com.unibague.backend.model.Dependency;
import com.unibague.backend.service.ServiceDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class ControllerDependency {

    @Autowired
    ServiceDependency serviceDependency;

    @GetMapping("/getDependencies")
    public List<Dependency> getDependencies() {
        return serviceDependency.getDependencies();
    }

    @PostMapping("/addDependency")
    public Boolean addDependency(@RequestBody HashMap<String, String> dependency) {
        return serviceDependency.addDependency(dependency);
    }

    @PostMapping("/addAllDependencies")
    public Boolean addAllDependencies() {
        return serviceDependency.addAllDependencies();
    }
}
