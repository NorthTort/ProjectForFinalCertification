package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/index")
    public String index(){
//        Получем объект аутентификации, и с помощью SecurityContextHolder.getContext() обращаемся к контексту и вызываем на нем метод аутентификации
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
////        Преобразовываем объект аутентификации в специальный объект класс по работе с пользователями
//        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
//        System.out.println(personDetails.getPerson().getId());
//        System.out.println(personDetails.getPerson().getLogin());
//        System.out.println(personDetails.getPerson().getPassword());
        return "index";
    }
}
