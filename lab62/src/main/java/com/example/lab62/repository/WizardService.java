package com.example.lab62.repository;

import com.example.lab62.pojo.Wizard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WizardService {
    @Autowired
    private WizardRepository repository;

    public WizardService(WizardRepository repository){
        this.repository = repository;
    }
    public List<Wizard> retrieveWizards() {
        return repository.findAll();
    }
//    public Wizard retriveWizardById(String id) {
//        return repository.getWizardByID(id);
//    }


    public Wizard addWizard(Wizard wizard){
        return repository.save(wizard);
    }
    public void delWizard(String id){
        repository.deleteById(id);
    }
    public Wizard updateWizard(Wizard wizard){
        return repository.save(wizard);
    }



}
