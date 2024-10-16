package com.mamoudou.spring.controller;

import com.mamoudou.spring.entity.Product;
import com.mamoudou.spring.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;


    @PostMapping(path = "/product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return new ResponseEntity<>(this.productRepository.save(product), HttpStatus.CREATED);
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
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
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
