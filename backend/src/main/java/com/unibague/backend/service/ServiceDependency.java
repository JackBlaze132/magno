package com.unibague.backend.service;

import com.unibague.backend.model.Dependency;
import com.unibague.backend.repository.RepositoryDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceDependency {

    @Autowired
    RepositoryDependency repositoryDependency;
    public List<Dependency> getDependencies() {
        return repositoryDependency.findAll();
    }
}
