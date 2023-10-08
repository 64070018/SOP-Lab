package com.example.lab62.repository;

import com.example.lab62.pojo.Wizard;
import com.example.lab62.pojo.Wizards;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WizardRepository extends MongoRepository<Wizard, String> {
    @Query(value = "{_id: '?0'}")
    public Wizard getWizardByID(String id);

}
