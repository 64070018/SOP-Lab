package com.example.lab3;

import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {
    private List<Customer> customers;
    public CustomerController(){
//        this.customers = new ArrayList();
        this.customers = new ArrayList();
        this.customers.add(new Customer("1010", "John", "Male", 25));
        this.customers.add(new Customer("1018", "Peter", "Male", 25));
        this.customers.add(new Customer("1019", "Sara", "Female", 25));
        this.customers.add(new Customer("1110", "Rose", "Female", 25));
        this.customers.add(new Customer("1001", "Emma", "Female", 25));
    }
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customer> getCustomers() {
        return this.customers;
    }


    @RequestMapping(value = "/customerbyid/{id}", method = RequestMethod.GET)
    public Customer getCustomerByID(@PathVariable String id) {
        System.out.println("test");
        for (int i = 0; i <= this.customers.size(); i++) {
            if (this.customers.get(i).getID().equals(id)) {
                return this.customers.get(i);
            }
        }
        return null;
    }
    @RequestMapping(value = "/customerbyname/{name}")
    public Customer getCustomerByName(@PathVariable String name){
        for (int i = 0; i <= this.customers.size(); i++) {
            if (this.customers.get(i).getName().equals(name)) {
                return this.customers.get(i);
            }
        }
        return null;
    }

    @RequestMapping(value = "/customerDelByid/{id}", method = RequestMethod.DELETE)
    public boolean delCustomerByID(@PathVariable String id){
        for (int i = 0; i <= this.customers.size(); i++) {
            if (this.customers.get(i).getID().equals(id)) {
                this.customers.remove(i);
                return true;
            }
        }
        return false;
    }
    @RequestMapping(value = "/customerDelByname/{name}", method = RequestMethod.DELETE)
    public boolean delCustomerByName(@PathVariable String name){
        for (int i = 0; i <= this.customers.size(); i++) {
            if (this.customers.get(i).getName().equals(name)) {
                this.customers.remove(i);
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
    public boolean addCustomer(@RequestParam String id, @RequestParam String name, @RequestParam String sex, @RequestParam int age){
//        this.customers.add(new Customer("1010", "John", "Male", 25));
        this.customers.add(new Customer(id, name, sex, age));
        return true;
    }

    @RequestMapping(value = "/addCustomer2", method = RequestMethod.POST)
    public boolean addCustomer2(@RequestParam String id, @RequestParam String name, @RequestParam String sex, @RequestParam int age){
//        this.customers.add(new Customer("1010", "John", "Male", 25));
        this.customers.add(new Customer(id, name, sex, age));
        return true;
    }

//    ไม่ต่างกันใช้อะไรผลลัพธ์ก้อเหมือนกันหมด
//    8 have error cuz not match method



}
