package com.unibague.backend.service;

import com.unibague.backend.model.InvestigationGroup;
import com.unibague.backend.repository.RepositoryInvestigationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceInvestigationGroup {

    @Autowired
    private RepositoryInvestigationGroup repositoryInvestigationGroup;

    public List<InvestigationGroup> getInvestigationGroups() {
        return repositoryInvestigationGroup.findAll();
    }
}
