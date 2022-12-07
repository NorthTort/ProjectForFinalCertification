package com.example.springsecurityapplication.servises;

import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service //Помечаем, что данный класс будет являться сервисом
@Transactional(readOnly = true) //Указываем, что все транзакции для чтения
public class ProductServise {

    private  final ProductRepository productRepository;

    @Autowired
    public ProductServise(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    Метод возвращает все продукты
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

//      Метод возвращает товар по id
    public Product getProductId(int id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null); //Возвращаем optional, если в нём нет product то возвращаем null
    }

//    Метод сохраняет объект, который пришёл с form
    @Transactional //Переопределяем transactional
    public void saveProduct(Product product){
        productRepository.save(product);
    }

//    Метод обновляет информацию о product
    @Transactional //Переопределяем transactional
    public  void updateProduct(int id, Product product){
        product.setId(id);
        productRepository.save(product);
    }

//    Метод удаляет product по id
    @Transactional //Переопределяем transactional
    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }
}
