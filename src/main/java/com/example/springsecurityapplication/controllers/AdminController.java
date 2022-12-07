package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.security.PersonDetails;
import com.example.springsecurityapplication.servises.ProductServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
//    Указали, что к данному классу имеет доступ только пользователь с ролью ADMIN
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

//    Указали, что к данному методу имеет доступ только пользователь с ролью ADMIN
//    @PreAuthorize("hasRole('ROLE_ADMIN')")

    private final ProductServise productServise;
    @Autowired
    public AdminController(ProductServise productServise) {
        this.productServise = productServise;
    }

//    Метод по отображению главной страницы администратора с выводом товаров
    @GetMapping()
    public String admin(Model model){
        //        Получем объект аутентификации, и с помощью SecurityContextHolder.getContext() обращаемся к контексту и вызываем на нем метод аутентификации
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //        Преобразовываем объект аутентификации в специальный объект класс по работе с пользователями
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        String role = personDetails.getPerson().getRole();

        if(role.equals("ROLE_USER")){
            return "redirect:/index";
        }

        model.addAttribute("products", productServise.getAllProduct());
        return "admin/admin";
    }

//    Метод по отображению формы добавления
    @GetMapping("/product/add") //Обработали тот путь на который у нас ведет ссылка добавить товар
    public String addProduct(Model model){
        model.addAttribute("product", new Product()); //Положили в модель пустой объект товара, чтобы потом его привязать к форме
        return "product/addProduct";
    }

//    Метод добавляет объект с формы в таблицу product
    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("product") Product product){
        productServise.saveProduct(product);
        return "redirect:/admin";
    }

//    Метод удаляет product
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productServise.deleteProduct(id);
        return "redirect:/admin";
    }

//    Метод по отображению формы редактирования product
    @GetMapping("/product/edit/{id}") //Обрабатываем нажатие на ссылку редактировать товар
    public String editProduct(@PathVariable("id") int id, Model model){ //Принимаем "id" и помещаем его в специальную переменную id
//    Кладем в модель атрибут нашей страницы editProduct.html и продукт, который получили по id
        model.addAttribute("editProduct", productServise.getProductId(id));
        return "product/editProduct";
    }

//    Метод редактирования product
    @PostMapping("/product/edit/{id}")
    public String editProduct(@ModelAttribute("editProduct") Product product, @PathVariable("id") int id){
        productServise.updateProduct(id, product);
        return "redirect:/admin";
    }
}
