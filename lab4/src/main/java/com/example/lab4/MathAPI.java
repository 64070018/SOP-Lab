package com.example.lab4;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MathAPI {
    @RequestMapping(value = "/plus/{num1}/{num2}", method = RequestMethod.GET)
    public double myPlus(@PathVariable double num1, @PathVariable double num2) {
        return num1 + num2;
    }
    @RequestMapping(value = "/minus/{num1}/{num2}", method = RequestMethod.GET)
    public double myMinus(@PathVariable double num1, @PathVariable double num2) {
        return num1 - num2;
    }
    @RequestMapping(value = "/divide/{num1}/{num2}", method = RequestMethod.GET)
    public double myDivide(@PathVariable double num1, @PathVariable double num2) {
        return num1 / num2;
    }
    @RequestMapping(value = "/multiply/{num1}/{num2}", method = RequestMethod.GET)
    public double myMulti(@PathVariable double num1, @PathVariable double num2) {
        return num1 * num2;
    }
    @RequestMapping(value = "/mod/{num1}/{num2}", method = RequestMethod.GET)
    public double myMod(@PathVariable double num1, @PathVariable double num2) {
        return num1 % num2;
    }
//    @RequestMapping(value = "/max", method = Po
    @RequestMapping(value = "/max", method = RequestMethod.POST)
    public double myMax(@RequestBody MultiValueMap<String, String> num) {
        Map<String, String> d = num.toSingleValueMap();
//        double out = Math.max(Double.parseDouble(d.get("num1")), Double.parseDouble(d.get("num2")));
        return Math.max(Double.parseDouble(d.get("num1")), Double.parseDouble(d.get("num2")));
    }
}