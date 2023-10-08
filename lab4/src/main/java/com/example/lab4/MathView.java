package com.example.lab4;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Route(value = "index1")
public class MathView extends VerticalLayout {
    private TextField n1, n2, a1;
    private Button btnPlus, btnMinus, btnMulti, btnDivide, btnMod, btnMax;
    public MathView() {
        n1 = new TextField("Number 1");
        n2 = new TextField("Number 2");
        a1 = new TextField("Answer");
        btnPlus = new Button("+");
        btnMinus = new Button("-");
        btnMulti = new Button("x");
        btnDivide = new Button("/");
        btnMod = new Button("Mod");
        btnMax = new Button("Max");
        HorizontalLayout h1 = new HorizontalLayout();
        Text opTitle = new Text("Operator");
        h1.add(btnPlus, btnMinus, btnMulti, btnDivide, btnMod, btnMax);
        add(n1, n2, opTitle, h1, a1);

        btnPlus.addClickListener(event -> {
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());
            String out = WebClient.create().get().uri("http://localhost:8080/plus/"+num1+"/"+num2)
                    .retrieve().bodyToMono(String.class).block();
            a1.setValue(out);
        });
        btnMinus.addClickListener(event -> {
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());
            String out = WebClient.create().get().uri("http://localhost:8080/minus/"+num1+"/"+num2)
                    .retrieve().bodyToMono(String.class).block();
            a1.setValue(out);
        });
        btnMulti.addClickListener(event -> {
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());
            String out = WebClient.create().get().uri("http://localhost:8080/multiply/"+num1+"/"+num2)
                    .retrieve().bodyToMono(String.class).block();
            a1.setValue(out);
        });
        btnDivide.addClickListener(event -> {
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());
            String out = WebClient.create().get().uri("http://localhost:8080/divide/"+num1+"/"+num2)
                    .retrieve().bodyToMono(String.class).block();
            a1.setValue(out);
        });
        btnMod.addClickListener(event -> {
            double num1 = Double.parseDouble(n1.getValue());
            double num2 = Double.parseDouble(n2.getValue());
            String out = WebClient.create().get().uri("http://localhost:8080/mod/"+num1+"/"+num2)
                    .retrieve().bodyToMono(String.class).block();
            a1.setValue(out);
        });
        btnMax.addClickListener(event -> {
//            double num1 = Double.parseDouble(n1.getValue());
//            double num2 = Double.parseDouble(n2.getValue());
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("num1", n1.getValue());
            formData.add("num2", n2.getValue());

//            อย่าหาใส่ / ข้างหลังเวลาจะส่งข้อมูล

            String out = WebClient.create().post().uri("http://localhost:8080/max")
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve().bodyToMono(String.class).block();
            a1.setValue(out);
        });

    }

}