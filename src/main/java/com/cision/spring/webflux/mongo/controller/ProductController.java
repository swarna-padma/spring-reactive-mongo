package com.cision.spring.webflux.mongo.controller;

import com.cision.spring.webflux.mongo.dto.ProductDTO;
import com.cision.spring.webflux.mongo.entity.Product;
import com.cision.spring.webflux.mongo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public Flux<ProductDTO> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDTO> getProduct(@PathVariable String id){
        return productService.getProduct(id);
    }

    @GetMapping("/productsInRange")
    public Flux<ProductDTO> getProductsInRange(@RequestParam("min") double min, @RequestParam("max") double max){
        return productService.getProductsInRange(min, max);
    }

    @PostMapping()
    public Mono<ProductDTO> saveProduct(@RequestBody Mono<ProductDTO> productDTO){
        return productService.saveProduct(productDTO);
    }

    @PutMapping("/updateProduct/{id}")
    public Mono<ProductDTO> updateProduct(@RequestBody Mono<ProductDTO> dtomono, @PathVariable String id){
        return productService.updateProduct(dtomono,id);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return productService.deleteProduct(id);
    }
}
