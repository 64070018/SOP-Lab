package com.example.lab5;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class WordPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    protected Word words;
    public WordPublisher(){
        this.words = new Word();
//        words.goodWords = new ArrayList<String>(Arrays.asList("happy", "enjoy", "like"));
//        words.badWords = new ArrayList<String>(Arrays.asList("fuck", "olo"));

    }

    @RequestMapping(value = "/addBad/{word}", method = RequestMethod.POST)
    public ArrayList<String> addBadWord(@PathVariable("word") String s){
        this.words.badWords.add(s);
        return this.words.badWords;
    }

    @RequestMapping(value = "/delBad/{word}", method = RequestMethod.GET)
    public ArrayList<String> deleteBadWord(@PathVariable("word") String s){
        this.words.badWords.remove(s);
        return this.words.badWords;

    }
    @RequestMapping(value = "/addGood/{word}", method = RequestMethod.POST)
    public ArrayList<String> addGoodWord(@PathVariable("word") String s){
        this.words.goodWords.add(s);
        return this.words.goodWords;
    }
    @RequestMapping(value = "/delGood/{word}", method = RequestMethod.GET)
    public ArrayList<String> deleteGoodWord(@PathVariable("word") String s){
        this.words.goodWords.remove(s);
        return this.words.goodWords;
    }
    @RequestMapping(value = "/proof/{sentence}", method = RequestMethod.POST)
    public String proofSentence(@PathVariable("sentence") String s){
        boolean good = false;
        boolean bad = false;
        for (String str : this.words.goodWords){
            System.out.println("check str good" + str);
            if (s.indexOf(str) > -1){
                good = true;
                System.out.println("check if good" + str + good);
            }
        }
        for (String str : this.words.badWords){
            System.out.println("check str bad" + str);
            if (s.indexOf(str) > -1){
                bad = true;
                System.out.println("check if bad" + str + bad);
            }
        }
        System.out.println("Bad "+ bad + "\n GOod " + good);
        if (good && bad){
            Object ans = rabbitTemplate.convertSendAndReceive("Fanout", "", s);
            return ((String) ans);
        } else if (good) {
            Object ans = rabbitTemplate.convertSendAndReceive("Direct", "good", s);
            return ((String) ans);
        } else if (bad) {
            Object ans = rabbitTemplate.convertSendAndReceive("Direct", "bad", s);
            return ((String) ans);
        }
        return "Not Found";
    }


    @RequestMapping(value = "/getSentence", method = RequestMethod.GET)
    public Sentence getSentence(){
        Sentence out = (Sentence) rabbitTemplate.convertSendAndReceive("Direct", "", "");
        return out;
    }
}
