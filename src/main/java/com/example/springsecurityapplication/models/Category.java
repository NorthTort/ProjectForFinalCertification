package com.example.springsecurityapplication.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> product;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProduct() {
        return product;
    }


}
