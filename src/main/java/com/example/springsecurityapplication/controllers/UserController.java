package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.security.PersonDetails;
import com.example.springsecurityapplication.servises.ProductServise;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    private final ProductServise productServise;

    public UserController(ProductServise productServise) {
        this.productServise = productServise;
    }


    @GetMapping("/index")
    public String index(Model model){
//        Получем объект аутентификации, и с помощью SecurityContextHolder.getContext() обращаемся к контексту и вызываем на нем метод аутентификации
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        Преобразовываем объект аутентификации в специальный объект класс по работе с пользователями
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        String role = personDetails.getPerson().getRole();

        if(role.equals("ROLE_ADMIN")){
            return "redirect:/admin";
        }

        model.addAttribute("products", productServise.getAllProduct());

        return "user/index";
    }

    @GetMapping("/info/{id}")
    public String infoProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productServise.getProductId(id));
        return "product/infoProduct";
    }
}
