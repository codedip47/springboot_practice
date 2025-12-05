package com.example.e_com.proj.Service;

import com.example.e_com.proj.Repository.ProductRepo;
import com.example.e_com.proj.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public Product getProductById(int id) {
        return repo.findById(id).get();
    }

    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = repo.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public Product addProduct(Product product) {
        return repo.save(product);
    }

    public Product updateProduct(int id, Product product) {
        Product product1 = repo.findById(id).get();
        if(product1 != null){
            product1.setAvailable(product.isAvailable());
            product1.setBrand(product.getBrand());
            product1.setCategory(product.getCategory());
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            product1.setQuantity(product.getQuantity());
            product1.setRelease_date(product.getRelease_date());
            product1.setDescription(product.getDescription());

            return repo.save(product1);
        }
        return null;
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }
}
