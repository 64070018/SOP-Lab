package com.example.lab3;

import org.springframework.web.bind.annotation.*;

@RestController
public class GeneratePasswordService {
    @RequestMapping(value = "/{name:[a-z]+}.generate", method = RequestMethod.GET)
    public String generate(@PathVariable("name") String name) {
//        double pass = ;

        return "Hi, " +  name + '\n' + "Your new password is " +  (int) (Math.random()*(1000000000));
    }
}