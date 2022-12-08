package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.models.Image;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.security.PersonDetails;
import com.example.springsecurityapplication.servises.ProductServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
//    Указали, что к данному классу имеет доступ только пользователь с ролью ADMIN
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

//    Указали, что к данному методу имеет доступ только пользователь с ролью ADMIN
//    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @Value("${upload.path}")
    private String uploadPuth;

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
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @RequestParam("file_one") MultipartFile file_two, @RequestParam("file_two") MultipartFile file_three, @RequestParam("file_three") MultipartFile file_four, @RequestParam("file_four") MultipartFile file_five, @RequestParam("file_five") MultipartFile file_one) throws IOException {

        if(bindingResult.hasErrors()){
            return "product/addProduct";
        }

        if(file_one != null){ //Проверка на пустоту
            File uploadDir = new File(uploadPuth); //Объект по хранению пути сохранения
            if(!uploadDir.exists()){ //Если путь не существует
                uploadDir.mkdir(); //Создаем этот путь
            }
            String uuidFile = UUID.randomUUID().toString(); //Создаем уникальное имя файла (UUID - уникальный идентификатор)
            String resultFileName = uuidFile + "." + file_one.getOriginalFilename();
            file_one.transferTo(new File(uploadPuth + "/" + resultFileName)); //Загружаем файл по указанному пути
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        if(file_two != null){ //Проверка на пустоту
            File uploadDir = new File(uploadPuth); //Объект по хранению пути сохранения
            if(!uploadDir.exists()){ //Если путь не существует
                uploadDir.mkdir(); //Создаем этот путь
            }
            String uuidFile = UUID.randomUUID().toString(); //Создаем уникальное имя файла (UUID - уникальный идентификатор)
            String resultFileName = uuidFile + "." + file_two.getOriginalFilename();
            file_two.transferTo(new File(uploadPuth + "/" + resultFileName)); //Загружаем файл по указанному пути
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        if(file_three != null){ //Проверка на пустоту
            File uploadDir = new File(uploadPuth); //Объект по хранению пути сохранения
            if(!uploadDir.exists()){ //Если путь не существует
                uploadDir.mkdir(); //Создаем этот путь
            }
            String uuidFile = UUID.randomUUID().toString(); //Создаем уникальное имя файла (UUID - уникальный идентификатор)
            String resultFileName = uuidFile + "." + file_three.getOriginalFilename();
            file_three.transferTo(new File(uploadPuth + "/" + resultFileName)); //Загружаем файл по указанному пути
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        if(file_four != null){ //Проверка на пустоту
            File uploadDir = new File(uploadPuth); //Объект по хранению пути сохранения
            if(!uploadDir.exists()){ //Если путь не существует
                uploadDir.mkdir(); //Создаем этот путь
            }
            String uuidFile = UUID.randomUUID().toString(); //Создаем уникальное имя файла (UUID - уникальный идентификатор)
            String resultFileName = uuidFile + "." + file_four.getOriginalFilename();
            file_four.transferTo(new File(uploadPuth + "/" + resultFileName)); //Загружаем файл по указанному пути
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        if(file_five != null){ //Проверка на пустоту
            File uploadDir = new File(uploadPuth); //Объект по хранению пути сохранения
            if(!uploadDir.exists()){ //Если путь не существует
                uploadDir.mkdir(); //Создаем этот путь
            }
            String uuidFile = UUID.randomUUID().toString(); //Создаем уникальное имя файла (UUID - уникальный идентификатор)
            String resultFileName = uuidFile + "." + file_five.getOriginalFilename();
            file_five.transferTo(new File(uploadPuth + "/" + resultFileName)); //Загружаем файл по указанному пути
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }
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
