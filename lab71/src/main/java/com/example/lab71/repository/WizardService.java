package com.example.lab71.repository;

import com.example.lab71.pojo.Wizard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WizardService {
    @Autowired
    private WizardRepository repository;

    public WizardService(WizardRepository repository){
        this.repository = repository;
    }

    @Cacheable(value = "wizard")
    public List<Wizard> retrieveWizards() {
        return repository.findAll();
    }

    @CacheEvict(value = "wizard")
    public Wizard addWizard(Wizard wizard){
        return repository.save(wizard);
    }

    @CacheEvict(value = "wizard")
    public void delWizard(String id){
        repository.deleteById(id);
    }

    @CachePut(value = "wizard")
    public Wizard updateWizard(Wizard wizard){
        return repository.save(wizard);
    }



}
