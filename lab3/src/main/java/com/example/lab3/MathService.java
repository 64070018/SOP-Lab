package com.example.lab3;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

@RestController
public class MathService {
    @RequestMapping(value = "/add/{num1}/{num2}", method = RequestMethod.GET)
    public double add(@PathVariable double num1, @PathVariable double num2) {
        return num1 + num2;
    }
    @RequestMapping(value = "/minus/{num1}/{num2}", method = RequestMethod.GET)
    public double minus(@PathVariable double num1, @PathVariable double num2) {
        return num1 - num2;
    }
    @RequestMapping(value = "/multiply", method = RequestMethod.GET)
    public double multiply(@RequestParam double num1, @RequestParam double num2) {
        return num1 * num2;
    }
    @RequestMapping(value = "/divide", method = RequestMethod.GET)
    public double divide(@RequestParam double num1, @RequestParam double num2) {
        return num1 / num2;
    }
}