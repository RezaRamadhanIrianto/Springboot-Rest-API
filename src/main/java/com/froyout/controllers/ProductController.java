package com.froyout.controllers;

import com.froyout.dto.ResponseData;
import com.froyout.models.entities.Product;
import com.froyout.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(consumes = MediaType.ALL_VALUE)
    public ResponseEntity<ResponseData<Product>> create(@Valid Product product, Errors errors){
        ResponseData<Product> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error: errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayLoad(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayLoad(productService.save(product));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable("id") Long id){
        return productService.findOne(id);
    }

    @PutMapping(consumes = MediaType.ALL_VALUE)
    public ResponseEntity<ResponseData<Product>> update(@Valid Product product, Errors errors){
        ResponseData<Product> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for(ObjectError error: errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayLoad(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayLoad(productService.save(product));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public Boolean delete( @PathVariable("id") Long id){
        productService.removeOne(id);
        return true;
    }

}
