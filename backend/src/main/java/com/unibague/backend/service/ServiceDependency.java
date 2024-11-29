package com.unibague.backend.service;

import com.unibague.backend.model.Dependency;
import com.unibague.backend.repository.RepositoryDependency;
import com.unibague.backend.util.ExceptionLogger;
import com.unibague.backend.util.FetchExternalData;
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
            ExceptionLogger.logException(e);
            return false;
        }
    }

    public List<String> getAllDependenciesNames(){
        return FetchExternalData.fetchDependencyNamesFromExternalData();
    }

    public Boolean addAllDependencies(){
        List<String> dependencies = getAllDependenciesNames();
        for (String dependency : dependencies) {
            if (!repositoryDependency.existsByName(dependency)) {
                Dependency d = new Dependency();
                d.setName(dependency);
                repositoryDependency.save(d);
            }
        }
        return true;
    }
}
