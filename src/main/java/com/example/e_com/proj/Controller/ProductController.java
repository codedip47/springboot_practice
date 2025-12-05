package com.example.e_com.proj.Controller;

import com.example.e_com.proj.Service.ProductService;
import com.example.e_com.proj.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;

//    @RequestMapping("/")
//    public String greet(){
//        return "Hello Dip!!!";
//    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllPrducts(){
        List<Product> products = service.getAllProducts().getBody();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable int id){
        return service.getProductById(id);
    }

    @PostMapping("/add-product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        System.out.println(product.getDescription());
        return new ResponseEntity<>(service.addProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product){
        Product product1 = null;
        try{
            product1 = service.updateProduct(id, product);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(product1, HttpStatus.OK);
    }

    @DeleteMapping("/delete-product/{id}")
    public void deleteProduct(@PathVariable int id){
        service.deleteProduct(id);
    }


}
