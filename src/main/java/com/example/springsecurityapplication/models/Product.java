package com.example.springsecurityapplication.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false, columnDefinition = "text", unique = true)
    @NotEmpty(message = "Наименование не может быть пустым")
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "text")
    @NotEmpty(message = "Описание товара не может быть пустым")
    private String description;

    @Column(name = "price", nullable = false)
    @Min(value = 1, message = "Цена товара не может быть отрицательной или 0")
    @NotNull(message = "Цена товара не может быть отрицательной или 0")
    private float price;

    @Column(name = "warehouse", nullable = false)
    @NotEmpty(message = "Склад по нахождению товара не может быть пустым")
    private String warehouse;

    @Column(name = "seller", nullable = false)
    @NotEmpty(message = "Продавец товара не может быть пустым")
    private String seller;

    public Product() {
    }

    public Product(String title, String description, float price, String warehouse, String seller) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.warehouse = warehouse;
        this.seller = seller;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public String getSeller() {
        return seller;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
