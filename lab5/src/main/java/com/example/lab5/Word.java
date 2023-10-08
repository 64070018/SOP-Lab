package com.example.lab5;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;

public class Word {
    public ArrayList<String> badWords;
    public ArrayList<String> goodWords;
    public Word() {
//        this.badWords = new ArrayList<>();
//        this.goodWords = new ArrayList<>();
        this.goodWords = new ArrayList<String>(Arrays.asList("happy", "enjoy", "like"));
        this.badWords = new ArrayList<String>(Arrays.asList("fuck", "olo"));
    }

}
