package com.example.lab5;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentenceConsumer{
    @Autowired
    private RabbitTemplate rabbitTemplate;
    protected Sentence sentences;
    public SentenceConsumer(){
        sentences = new Sentence();
    }
    @RabbitListener(queues = "BadWordQueue")
    public String addBadSentence(String s){
        this.sentences.badSentences.add(s);
        System.out.println("In addGood Sentence Method : " + sentences.goodSentences);
        return "Found Bad";
    }
    @RabbitListener(queues = "GoodWordQueue")
    public String addGoodSentence(String s){
        this.sentences.goodSentences.add(s);
        System.out.println("In addBad Sentence Method : " + sentences.goodSentences);
        return "Found Good";
    }

    @RabbitListener(queues = "GetQueue")
    public Sentence getSentence(){
        return this.sentences;
    }
}
