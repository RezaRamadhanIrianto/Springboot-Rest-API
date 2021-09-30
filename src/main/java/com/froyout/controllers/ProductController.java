package com.froyout.controllers;

import com.froyout.models.entities.Product;
import com.froyout.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(consumes = MediaType.ALL_VALUE)
    public Product create(Product product){
        return productService.save(product);
    }

    @GetMapping
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findOne( @PathVariable("id") Long id){
        return productService.findOne(id);
    }

    @PutMapping(consumes = MediaType.ALL_VALUE)
    public Product update(Product product){
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public Boolean delete( @PathVariable("id") Long id){
        productService.removeOne(id);
        return true;
    }

}
