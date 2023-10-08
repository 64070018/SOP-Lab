package com.example.lab5;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Route("/")
public class MyView2  extends HorizontalLayout {
    private TextField addWord, addSen;
    private Button addGood, addBad, addSenbtn, show;
    private ComboBox goodWords, badWords;
    private TextArea goodSen, badSen;

    public Word words = new Word();
    private FormLayout v1, v2;
    public MyView2() {
        v1 = new FormLayout();
        v2 = new FormLayout();
        addWord = new TextField("Add Word");
        addSen = new TextField("Add Sentence");
        addGood = new Button("Add good word");
        addBad = new Button("Add bad word");
        goodWords = new ComboBox("Good Words");
        goodWords.setItems(words.goodWords);
        badWords = new ComboBox("Bad Words");
        badWords.setItems(words.badWords);
        v1.add(addWord, addGood, addBad, goodWords, badWords);
        goodSen = new TextArea("Good Sentences");
        badSen = new TextArea("Bad Sentences");
        addSenbtn = new Button("Add Sentence");
        show = new Button("Show Sentence");
        v2.add(addSen, addSenbtn, goodSen, badSen, show);
        add(v1, v2);

        addGood.addClickListener(event -> {
            ArrayList output = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/addGood/"+addWord.getValue())
                    .retrieve()
                    .bodyToMono(ArrayList.class)
                    .block();
            System.out.println(output);
            goodWords.setItems(output);
        });
        addBad.addClickListener(event -> {
            ArrayList output = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/addBad/"+addWord.getValue())
                    .retrieve()
                    .bodyToMono(ArrayList.class)
                    .block();
            badWords.setItems(output);
        });

        addSenbtn.addClickListener(event -> {
            String output = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/proof/"+addSen.getValue())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            new Notification(output, 10000).open();
        });

        show.addClickListener(event -> {
            Sentence output = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/getSentence")
                    .retrieve()
                    .bodyToMono(Sentence.class)
                    .block();
            goodSen.setValue(String.valueOf(output.goodSentences));
            badSen.setValue(String.valueOf(output.badSentences));

        });


    }

}
