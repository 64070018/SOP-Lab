package com.example.lab71.View;

import com.example.lab71.pojo.Wizard;
import com.example.lab71.pojo.Wizards;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Route("/mainPage.it")
public class MainWizardView extends VerticalLayout {
    TextField name, price;
    ComboBox<String> school, house, position;
    Button back, create, update, delete, next;
    RadioButtonGroup<String> gender;
//    private List<Wizard> data;
    private Wizards data;
    private int index = 0;
    public MainWizardView() {
        data = new Wizards();
        name = new TextField();
        showData();
        name.setPlaceholder("Fullname");
        System.out.println(data.getModel().get(index).getName());
        name.setValue(data.getModel().get(index).getName());

        price = new TextField("Dollars");
        price.setPrefixComponent(new Span("$"));
        price.setValue(String.valueOf(data.getModel().get(index).getMoney()));

        gender = new RadioButtonGroup<>();
        gender.setLabel("Gender : ");
        gender.setItems("Male", "Female");
        if (data.getModel().get(index).getSex().equals("m")){
            this.gender.setValue("Male");
        }else if (data.getModel().get(index).getSex().equals("f")){
            gender.setValue("Female");
        }

        position = new ComboBox<>();
        position.setItems("Student","Teacher");
        position.setPlaceholder("Position");
        position.setValue(data.getModel().get(index).getPosition());

        school = new ComboBox<>();
        school.setItems("Hogwarts", "Beauxbatons", "Durmstrang");
        school.setPlaceholder("School");
        school.setValue(data.getModel().get(index).getSchool());


        house = new ComboBox<>();
        house.setItems("Gryffindor", "Ravenclaw", "Fufflepuff", "Slyther");
        house.setPlaceholder("House");
        house.setValue(data.getModel().get(index).getHouse());

        back = new Button("<<");
        create = new Button("create");
        update = new Button("update");
        delete = new Button("delete");
        next = new Button(">>");
        HorizontalLayout h1 = new HorizontalLayout();
        h1.add(back, create, update, delete, next);
        add(name, gender, position, price, school, house, h1);

        create.addClickListener(event -> {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("name", name.getValue());
            formData.add("gender", gender.getValue());
            formData.add("position", position.getValue());
            formData.add("price", price.getValue());
            formData.add("school", school.getValue());
            formData.add("house", house.getValue());

            String output = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/addWizard")
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve().bodyToMono(String.class).block();
            this.index = this.showData().size() - 1;
            this.data.setModel((ArrayList<Wizard>) this.showData());
            System.out.println(this.index + " index now");
            new Notification(output, 10000).open();
        });
        update.addClickListener(event -> {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("id", data.getModel().get(index).get_id());
            formData.add("name", name.getValue());
            formData.add("gender", gender.getValue());
            formData.add("position", position.getValue());
            formData.add("price", price.getValue());
            formData.add("school", school.getValue());
            formData.add("house", house.getValue());

            String output = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/updateWizard")
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve().bodyToMono(String.class).block();
            this.data.setModel((ArrayList<Wizard>) this.showData());
            new Notification(output, 10000).open();
        });

        delete.addClickListener(event -> {
            String output = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/deleteWizard/" + data.getModel().get(index).get_id())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            new Notification(output, 10000).open();
            this.data.setModel((ArrayList<Wizard>) this.showData());
//            this.index = 0;
            fieldData();
//            รีข้อมูลเอาอันหลังมาใส่ เอาindex มาก่อนเลย
        });









        next.addClickListener(event -> {
            this.index++;
            if (this.index >= this.data.getModel().toArray().length){
//              size - 1 = index
                this.index = this.showData().size()-1;
            }
            name.setValue(data.getModel().get(index).getName());
            price.setValue(String.valueOf(data.getModel().get(index).getMoney()));
            if (data.getModel().get(index).getSex().equals("m")){
                this.gender.setValue("Male");
            }else if (data.getModel().get(index).getSex().equals("f")){
                gender.setValue("Female");
            }
            position.setValue(data.getModel().get(index).getPosition());
            school.setValue(data.getModel().get(index).getSchool());
            house.setValue(data.getModel().get(index).getHouse());
        });
        back.addClickListener(event -> {
            this.index--;
            if (this.index < 0){
                this.index = 0;
            }

            name.setValue(data.getModel().get(index).getName());
            price.setValue(String.valueOf(data.getModel().get(index).getMoney()));
            if (data.getModel().get(index).getSex().equals("m")){
                this.gender.setValue("Male");
            }else if (data.getModel().get(index).getSex().equals("f")){
                gender.setValue("Female");
            }
            position.setValue(data.getModel().get(index).getPosition());
            school.setValue(data.getModel().get(index).getSchool());
            house.setValue(data.getModel().get(index).getHouse());
        });

    }
    public List<Wizard> showData(){
        List<Wizard> output = WebClient.create()
                .get()
                .uri("http://localhost:8080/wizards")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Wizard>>() {})
                .block();
        this.data.setModel((ArrayList<Wizard>) output);
        return output;
    }
    public void fieldData(){
        if (this.index >= this.data.getModel().toArray().length){
            this.index = this.showData().size()-1;
        }
        name.setValue(data.getModel().get(index).getName());
        gender.setValue(data.getModel().get(index).getSex());
        position.setValue(data.getModel().get(index).getPosition());
        price.setValue(data.getModel().get(index).getMoney());
        school.setValue(data.getModel().get(index).getSchool());
        house.setValue(data.getModel().get(index).getHouse());
    }

}
