package com.unibague.backend.service;

import com.unibague.backend.model.ResearchSeedbed;
import com.unibague.backend.repository.RepositoryResearchSeedbed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceResearchSeedbed {

    @Autowired
    RepositoryResearchSeedbed repositoryResearchSeedbed;

    public List<ResearchSeedbed> getResearchSeedbeds(){
        return repositoryResearchSeedbed.findAll();
    }
}
