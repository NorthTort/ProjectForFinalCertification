package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.servises.ProductServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class MainController {

    private  final ProductServise productServise;

    @Autowired
    public MainController(ProductServise productServise) {
        this.productServise = productServise;
    }

//    Метод предназначен для отображения товаров без прохождения аунтефикации и валидации
    @GetMapping("")
    public String getAllProduct(Model model){
        model.addAttribute("products", productServise.getAllProduct());
        return "product/product";
    }

    @GetMapping("/info/{id}")
    public String infoProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productServise.getProductId(id));
        return "product/infoProduct";
    }
}
