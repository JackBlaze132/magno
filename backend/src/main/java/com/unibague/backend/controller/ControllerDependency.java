package com.unibague.backend.controller;

import com.unibague.backend.model.Dependency;
import com.unibague.backend.service.ServiceDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerDependency {

    @Autowired
    ServiceDependency serviceDependency;

    @GetMapping("/getDependencies")
    public List<Dependency> getDependencies() {
        return serviceDependency.getDependencies();
    }
}
