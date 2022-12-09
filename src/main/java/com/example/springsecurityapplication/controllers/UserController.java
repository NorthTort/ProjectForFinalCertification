package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.models.Cart;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.CartRepositury;
import com.example.springsecurityapplication.repositories.ProductRepository;
import com.example.springsecurityapplication.security.PersonDetails;
import com.example.springsecurityapplication.servises.ProductServise;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final CartRepositury cartRepositury;
    private final ProductRepository productRepository;
    private final ProductServise productServise;

    public UserController(CartRepositury cartRepositury, ProductRepository productRepository, ProductServise productServise) {
        this.cartRepositury = cartRepositury;
        this.productRepository = productRepository;
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

//    Добавить товар в корзину
    @GetMapping("/cart/add/{id}")
    public String addProductInCart(@PathVariable("id") int id, Model model){
        Product product = productServise.getProductId(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        int id_person = personDetails.getPerson().getId();
        Cart cart = new Cart(id_person, product.getId());

        cartRepositury.save(cart);

        return "redirect:/cart";

    }

    @GetMapping("/cart")
    public String cart(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        int id_person = personDetails.getPerson().getId();

        List<Cart> cartList = cartRepositury.findByPersonId(id_person);
        List<Product> productList = new ArrayList<>();

        for(Cart cart : cartList){
            productList.add(productServise.getProductId(cart.getPersonId()));
        }

        float price = 0;
        for(Product product : productList){
            price += product.getPrice();
        }

        model.addAttribute("price", price);
        model.addAttribute("cart_product", productList);
        return "user/cart";
    }

    @GetMapping("/info/{id}")
    public String infoProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productServise.getProductId(id));
        return "product/infoProduct";
    }

    @PostMapping("/product/search")
    public String productSearch(@RequestParam("search") String search, @RequestParam("ot") String Ot, @RequestParam("do") String Do, @RequestParam(value = "price", required = false, defaultValue = "") String price, @RequestParam(value = "category", required = false, defaultValue = "") String category, Model model){

        //*1234
        if(!search.isEmpty() && !Ot.isEmpty() && !Do.isEmpty() && !price.isEmpty() && !category.isEmpty()){
            if(price.equals("ascending_price")){
                switch (category){
                    case ("candle"):
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPrice(search, Float.parseFloat(Ot), Float.parseFloat(Do), 1));
                        break;
                    case ("materials"):
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPrice(search, Float.parseFloat(Ot), Float.parseFloat(Do), 2));
                        break;
                    case ("accessories"):
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPrice(search, Float.parseFloat(Ot), Float.parseFloat(Do), 3));
                        break;
                }
            } else if(price.equals("descending_price")){
                switch (category){
                    case ("candle"):
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search, Float.parseFloat(Ot), Float.parseFloat(Do), 1));
                        break;
                    case ("materials"):
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search, Float.parseFloat(Ot), Float.parseFloat(Do), 2));
                        break;
                    case ("accessories"):
                        model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search, Float.parseFloat(Ot), Float.parseFloat(Do), 3));
                        break;
                }
            }
        }

        //*234
        if(search.isEmpty() && !Ot.isEmpty() && !Do.isEmpty() && !price.isEmpty() && !category.isEmpty()){
            if(price.equals("ascending_price")){
                switch (category){
                    case ("candle"):
                        model.addAttribute("search_product", productRepository.CategoryPriceGreaterThanEqualAndPriceLessThanOrderByPrice(Float.parseFloat(Ot), Float.parseFloat(Do), 1));
                        break;
                    case ("materials"):
                        model.addAttribute("search_product", productRepository.CategoryPriceGreaterThanEqualAndPriceLessThanOrderByPrice(Float.parseFloat(Ot), Float.parseFloat(Do), 2));
                        break;
                    case ("accessories"):
                        model.addAttribute("search_product", productRepository.CategoryPriceGreaterThanEqualAndPriceLessThanOrderByPrice(Float.parseFloat(Ot), Float.parseFloat(Do), 3));
                        break;
                }
            } else if(price.equals("descending_price")){
                switch (category){
                    case ("candle"):
                        model.addAttribute("search_product", productRepository.CategoryPriceGreaterThanEqualAndPriceLessThanOrderByPriceDesc(Float.parseFloat(Ot), Float.parseFloat(Do), 1));
                        break;
                    case ("materials"):
                        model.addAttribute("search_product", productRepository.CategoryPriceGreaterThanEqualAndPriceLessThanOrderByPriceDesc(Float.parseFloat(Ot), Float.parseFloat(Do), 2));
                        break;
                    case ("accessories"):
                        model.addAttribute("search_product", productRepository.CategoryPriceGreaterThanEqualAndPriceLessThanOrderByPriceDesc(Float.parseFloat(Ot), Float.parseFloat(Do), 3));
                        break;
                }
            }
        }


        //*123
        if(!search.isEmpty() && !Ot.isEmpty() && !Do.isEmpty() && !price.isEmpty() && category.isEmpty()){
            if(price.equals("ascending_price")){
                model.addAttribute("search_product", productRepository.findByTitleOrderByPrice(search, Float.parseFloat(Ot), Float.parseFloat(Do)));
            } else if(price.equals("descending_price")){
                model.addAttribute("search_product", productRepository.findByTitleOrderByPriceDesc(search, Float.parseFloat(Ot), Float.parseFloat(Do)));
            }
        }

        //*34
        if(search.isEmpty() && Ot.isEmpty() && Do.isEmpty() && !price.isEmpty() && !category.isEmpty()){
            if(price.equals("ascending_price")){
                switch (category){
                    case ("candle"):
                        model.addAttribute("search_product", productRepository.CategoryOrderByPrice(1));
                        break;
                    case ("materials"):
                        model.addAttribute("search_product", productRepository.CategoryOrderByPrice(2));
                        break;
                    case ("accessories"):
                        model.addAttribute("search_product", productRepository.CategoryOrderByPrice(3));
                        break;
                }
            } else if(price.equals("descending_price")){
                switch (category){
                    case ("candle"):
                        model.addAttribute("search_product", productRepository.CategoryOrderByPriceDesc(1));
                        break;
                    case ("materials"):
                        model.addAttribute("search_product", productRepository.CategoryOrderByPriceDesc(2));
                        break;
                    case ("accessories"):
                        model.addAttribute("search_product", productRepository.CategoryOrderByPriceDesc(3));
                        break;
                }
            }
        }

        //*24
        if(search.isEmpty() && !Ot.isEmpty() && !Do.isEmpty() && price.isEmpty() && !category.isEmpty()){
            switch (category){
                case ("candle"):
                    model.addAttribute("search_product", productRepository.CategoryAndPriceGreaterThanEqualAndPriceLessThan(Float.parseFloat(Ot), Float.parseFloat(Do), 1));
                    break;
                case ("materials"):
                    model.addAttribute("search_product", productRepository.CategoryAndPriceGreaterThanEqualAndPriceLessThan(Float.parseFloat(Ot), Float.parseFloat(Do), 2));
                    break;
                case ("accessories"):
                    model.addAttribute("search_product", productRepository.CategoryAndPriceGreaterThanEqualAndPriceLessThan(Float.parseFloat(Ot), Float.parseFloat(Do), 3));
                    break;
            }
        }

        //*23
        if(search.isEmpty() && !Ot.isEmpty() && !Do.isEmpty() && !price.isEmpty() && category.isEmpty()){
            if(price.equals("ascending_price")){
                model.addAttribute("search_product", productRepository.PriceGreaterThanEqualAndPriceLessThanOrderByPrice(Float.parseFloat(Ot), Float.parseFloat(Do)));
            } else if(price.equals("descending_price")){
                model.addAttribute("search_product", productRepository.PriceGreaterThanEqualAndPriceLessThanOrderByPriceDesc(Float.parseFloat(Ot), Float.parseFloat(Do)));
            }
        }

        //*12
        if(!search.isEmpty() && !Ot.isEmpty() && !Do.isEmpty() && price.isEmpty() && category.isEmpty()){
            model.addAttribute("search_product", productRepository.findByTitleAndPriceGreaterThanEqualAndPriceLessThan(search, Float.parseFloat(Ot), Float.parseFloat(Do)));
        }

        //*13
        if(!search.isEmpty() && Ot.isEmpty() && Do.isEmpty() && !price.isEmpty() && category.isEmpty()){
            if(price.equals("ascending_price")){
                model.addAttribute("search_product", productRepository.findByTitleAndOrderByPrice(search));
            } else if(price.equals("descending_price")){
                model.addAttribute("search_product", productRepository.findByTitleAndOrderByPriceDesc(search));
            }
        }

        //*14
        if (!search.isEmpty() && Ot.isEmpty() && Do.isEmpty() && price.isEmpty() && !category.isEmpty()){
            switch (category){
                case ("candle"):
                    model.addAttribute("search_product", productRepository.findByTitleAndCategory(search, 1));
                    break;
                case ("materials"):
                    model.addAttribute("search_product", productRepository.findByTitleAndCategory(search, 2));
                    break;
                case ("accessories"):
                    model.addAttribute("search_product", productRepository.findByTitleAndCategory(search, 3));
                    break;
            }
        }

        //*1
        if(!search.isEmpty() && Ot.isEmpty() && Do.isEmpty() && price.isEmpty() && category.isEmpty()){
            model.addAttribute("search_product", productRepository.findByTitleContainingIgnoreCase(search));
        }

        //*2
        if(search.isEmpty() && !Ot.isEmpty() && !Do.isEmpty() && price.isEmpty() && category.isEmpty()){
            model.addAttribute("search_product", productRepository.findByPriceGreaterThanEqualAndPriceLessThan(Float.parseFloat(Ot), Float.parseFloat(Do)));
        }

        //*3
        if(search.isEmpty() && Ot.isEmpty() && Do.isEmpty() && !price.isEmpty() && category.isEmpty()){
            if(price.equals("ascending_price")){
                model.addAttribute("search_product", productRepository.OrderByPrice());
            } else if (price.equals("descending_price")) {
                model.addAttribute("search_product", productRepository.OrderByPriceDesc());
            }
        }

        //*4
        if(search.isEmpty() && Ot.isEmpty() && Do.isEmpty() && price.isEmpty() && !category.isEmpty()){
            switch (category){
                case ("candle"):
                    model.addAttribute("search_product", productRepository.findByCategory(1));
                    break;
                case ("materials"):
                    model.addAttribute("search_product", productRepository.findByCategory(2));
                    break;
                case ("accessories"):
                    model.addAttribute("search_product", productRepository.findByCategory(3));
                    break;
            }
        }

        model.addAttribute("value_search", search);
        model.addAttribute("value_ot", Ot);
        model.addAttribute("value_do", Do);
        model.addAttribute("products", productServise.getAllProduct());

        return "user/index";
    }
}
