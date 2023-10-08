package com.example.lab3;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class Customer {
    private String ID, name;
    private boolean sex;
    private int age;
    public Customer(){
        new Customer("", null, false, 0);
    }
    public Customer(String ID, String n, boolean s, int a){
        setSex(s);
        setAge(a);
        setID(ID);
        setName(n);
    }
    public Customer(String ID, String n, String s, int a){
        setSex(s);
        setAge(a);
        setID(ID);
        setName(n);
    }

    public String getID() {
        return this.ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean getSex() {
        return sex;
    }
    public void setSex(boolean sex) {
        this.sex = sex;
    }
    public void setSex(String sex){
        if (sex == "Male" || sex == "male"){
            this.sex = false;
        } else if (sex == "female" || sex == "Female") {
            this.sex = true;
        }
    }

    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        if (age > 0){
            this.age = age;
        }else {
            this.age = 0;
        }
    }

}
