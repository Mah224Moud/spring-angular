package com.mamoudou.spring.controller;

import com.mamoudou.spring.entity.Product;
import com.mamoudou.spring.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping()
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final ProductRepository productRepository;


    @PostMapping(path = "/product")
    public ResponseEntity<?> addProduct(@RequestBody @Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            result.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Product savedProduct = this.productRepository.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }


    @GetMapping(path = "/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(this.productRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Optional<Product> product = this.productRepository.findById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/products/update")
    public ResponseEntity<?> updateProduct(@RequestBody @Valid Product product, BindingResult result){
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            result.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Optional<Product> existingProduct = this.productRepository.findById(product.getId());
        if(existingProduct.isPresent()){
            Product newProduct = existingProduct.get();
            newProduct.setName(product.getName());
            newProduct.setDescription(product.getDescription());
            newProduct.setPrice(product.getPrice());

            this.productRepository.save(newProduct);
            return new ResponseEntity<>(newProduct, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/products/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteProuct(@PathVariable int id){
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isPresent()){
            this.productRepository.delete(product.get());
            return new ResponseEntity<>(Map.of("message", product.get().getName() + " est supprimé(e) avec succès"), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
