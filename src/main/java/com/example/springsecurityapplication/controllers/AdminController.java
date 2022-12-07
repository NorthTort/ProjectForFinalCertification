package com.example.springsecurityapplication.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
//    Указали, что к данному классу имеет доступ только пользователь с ролью ADMIN
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

//    Указали, что к данному методу имеет доступ только пользователь с ролью ADMIN
//    @PreAuthorize("hasRole('ROLE_ADMIN')")


    @GetMapping()
    public String admin(){
        return "admin/admin";
    }
}
