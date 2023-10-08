package com.example.lab72.View;

import com.example.lab72.POJO.Product;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Route("/")
public class ProductView extends VerticalLayout {
    private TextField name;
    private NumberField price, cost, profit;
    private ComboBox<String> product;
    private Button ad, update, delete, clear;
    private Product pro;
    private List<String> proName;
    private List<Product> products;

    public ProductView() {
        proName = new ArrayList<>();
        products = new ArrayList<Product>();
        name = new TextField("Product Name : ");
        name.setWidth("600px");
        cost = new NumberField("Product Cost : ");
        cost.setWidth("600px");
        profit = new NumberField("Product Profit : ");
        profit.setWidth("600px");
        price = new NumberField("Product Price: ");
        price.setWidth("600px");
        price.setEnabled(false);

        ad = new Button("Add Product");
        update = new Button("Update Product");
        delete = new Button("Delete Product");
        clear = new Button("Clear Product");

        product = new ComboBox<>("Product List");
//        product.setItems("apple", "banana", "orage");
        product.setWidth("600px");

        setInfo("", 0.0, 0.0, 0.0);

        HorizontalLayout h1 = new HorizontalLayout();
        h1.add(ad, update, delete, clear);
        add(product, name, cost, profit, price, h1);
        loadProduct();




//        ACTION EVENT
        ad.addClickListener(event -> {
            getPrice();
            Product newProduct = new Product(null, this.name.getValue(), this.cost.getValue(), this.profit.getValue(), this.price.getValue());
            boolean status = WebClient.create().post().uri("http://localhost:8080/addProduct")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(newProduct)
                    .retrieve().bodyToMono(boolean.class).block();
            String text = status ? "Added" : "something not found";
            setInfo("", 0.0, 0.0, 0.0);
            loadProduct();
            new Notification(text, 5000).open();

        });

        update.addClickListener(event -> {
            getPrice();
            Product newProduct = new Product(this.pro.get_id(), this.name.getValue(), this.cost.getValue(), this.profit.getValue(), this.price.getValue());
            boolean status = WebClient.create().post().uri("http://localhost:8080/updateProduct")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(newProduct)
                    .retrieve().bodyToMono(boolean.class).block();
            String text = status ? "Updated" : "something not found";
            setInfo("", 0.0, 0.0, 0.0);
            new Notification(text, 5000).open();
            loadProduct();
        });
        delete.addClickListener(event -> {
            getPrice();
            Product newProduct = new Product(this.pro.get_id(), this.name.getValue(), this.cost.getValue(), this.profit.getValue(), this.price.getValue());
            boolean status = WebClient.create().post().uri("http://localhost:8080/deleteProduct")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(newProduct)
                    .retrieve().bodyToMono(boolean.class).block();
            String text = status ? "Deleted" : "something not found";
            setInfo("", 0.0, 0.0, 0.0);
            loadProduct();
            new Notification(text, 5000).open();
        });





        cost.addKeyPressListener(Key.ENTER, event -> {
            getPrice();
        });
        profit.addKeyPressListener(Key.ENTER, event -> {
            getPrice();
        });
        clear.addClickListener(event -> {
            setInfo("", 0.0, 0.0, 0.0);
        });


        product.addValueChangeListener(event -> {
            this.pro = WebClient.create().get().uri("http://localhost:8080/getProductByName/"+product.getValue())
                    .retrieve()
                    .bodyToMono(Product.class)
                    .block();
            System.out.println("112" + pro);
            if (this.pro != null) {
//                setInfo(this.pro.getProductName(), this.pro.getProductCost(),this.pro.getProductProfit(), this.pro.getProductPrice());
                this.name.setValue(this.pro.getProductName());
                this.cost.setValue(this.pro.getProductCost());
                this.profit.setValue(this.pro.getProductProfit());
                this.price.setValue(this.pro.getProductPrice());
            }
        });

    }

    public void setInfo(String name, double price, double cost, double profit){
        this.name.setValue(name);
        this.price.setValue(price);
        this.cost.setValue(cost);
        this.profit.setValue(profit);
    }

    public void getPrice(){
        Double output = WebClient.create()
                .get()
                .uri("http://localhost:8080/getPrice/" + cost.getValue()+ "/" + profit.getValue())
                .retrieve().bodyToMono(Double.class).block();
        price.setValue(output);

    }
    public void loadProduct(){
        this.proName.clear();
        this.products = WebClient.create()
                .get()
                .uri("http://localhost:8080/getAllProduct")
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<Product>>() {})
                .block();
        for (Product product : this.products) {
            proName.add(product.getProductName());
        }
//        proName have data but can't setItem
        product.setItems(proName);
    }



}
