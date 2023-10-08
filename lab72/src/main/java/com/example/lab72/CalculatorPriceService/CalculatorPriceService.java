package com.example.lab72.CalculatorPriceService;

import com.example.lab72.ProductService.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorPriceService {
    public Double getPrice(Double cost, Double profit){
        return cost+profit;
    }
}
