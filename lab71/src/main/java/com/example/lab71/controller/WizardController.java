package com.example.lab71.controller;

import com.example.lab71.pojo.Wizard;
import com.example.lab71.repository.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class WizardController {
    @Autowired
    private WizardService wizardService;
    @RequestMapping(value = "/wizards", method = RequestMethod.GET)
    public List<Wizard> getData() {
        List<Wizard> wizards = wizardService.retrieveWizards();
        return wizards;
    }

    @RequestMapping(value = "/addWizard", method = RequestMethod.POST)
    public ResponseEntity<String> addWizard(@RequestBody MultiValueMap<String, String> fromData){
        Map<String, String> data = fromData.toSingleValueMap();
//        data.get("name")  data.get("school")
        Wizard wizard = wizardService.addWizard(new Wizard(data.get("gender"), data.get("name"), data.get("school"), data.get("house"), data.get("price"), data.get("position")));
        return ResponseEntity.ok("Create Success!!");
    }
    @RequestMapping(value ="/deleteWizard/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> delWizard(@PathVariable String id){
        System.out.println(id);
        System.out.println(id.getClass());
        wizardService.delWizard(id);
        return ResponseEntity.ok("Delete success!!");
    }
    @RequestMapping(value ="/updateWizard", method = RequestMethod.POST)
    public ResponseEntity<String> upWizard(@RequestBody MultiValueMap<String, String> fromData){
        Map<String, String> data = fromData.toSingleValueMap();
        wizardService.updateWizard(new Wizard(data.get("id"), data.get("gender"), data.get("name"), data.get("school"), data.get("house"), data.get("price"), data.get("position")));
        return ResponseEntity.ok("update success!!");
    }











//    @RequestMapping(value = "/wizardByid/{id}", method = RequestMethod.GET)
//    public Wizard getDataByID(@PathVariable String id) {
//        Wizard wizards = wizardService.retriveWizardById(id);
//        System.out.println(wizards);
//        return wizards;
//    }
//    @RequestMapping(value = "/addWizard", method = RequestMethod.POST)
//    public ResponseEntity<Wizard> addData(@RequestBody MultiValueMap<String, String> formdata) {
//        Map<String, String> data = formdata.toSingleValueMap();
//        Wizard wizard = wizardService.addWizard(
//                new Wizard(data.get("name"), data.get("sex"), data.get("school"), data.get("house"), Integer.parseInt(data.get("money")), data.get("position"))
//        );
//        return ResponseEntity.ok(wizard);
//    }
}
