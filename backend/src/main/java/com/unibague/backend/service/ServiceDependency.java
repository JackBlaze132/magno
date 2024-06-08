package com.unibague.backend.service;

import com.unibague.backend.model.Dependency;
import com.unibague.backend.repository.RepositoryDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ServiceDependency {

    @Autowired
    RepositoryDependency repositoryDependency;
    public List<Dependency> getDependencies() {
        return repositoryDependency.findAll();
    }

    public Boolean addDependency(HashMap<String, String> dependency) {
        try{
            Dependency d = new Dependency();
            d.setName(dependency.get("name"));
            repositoryDependency.save(d);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
