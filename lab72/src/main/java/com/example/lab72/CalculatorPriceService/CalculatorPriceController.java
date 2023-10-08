package com.example.lab72.CalculatorPriceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorPriceController {
    @Autowired
    private CalculatorPriceService calService;

    @RequestMapping("/getPrice/{cost}/{profit}")
    public Double getPrice(@PathVariable Double cost, @PathVariable Double profit){
        return calService.getPrice(cost, profit);
    }
}
