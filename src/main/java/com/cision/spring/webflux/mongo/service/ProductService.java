package com.cision.spring.webflux.mongo.service;

import com.cision.spring.webflux.mongo.dto.ProductDTO;
import com.cision.spring.webflux.mongo.repository.ProductRepository;
import com.cision.spring.webflux.mongo.utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDTO> getProducts(){
        return productRepository.findAll().map(EntityUtils::entitytoDTO);
    }

    public Mono<ProductDTO> getProduct(String id){
        return productRepository.findById(id).map(EntityUtils::entitytoDTO);
    }

    public Flux<ProductDTO> getProductsInRange(double min, double max){
        return productRepository.findByPriceBetween(Range.closed(min,max));
    }

    public Mono<ProductDTO> saveProduct(Mono<ProductDTO> dtoMono){
       //convert dto to entity
       return dtoMono.map(EntityUtils:: dtotoEntity)
               .flatMap(productRepository:: insert)
               .map(EntityUtils::entitytoDTO);
    }
    public Mono<ProductDTO> updateProduct(Mono<ProductDTO> dtoMono, String id){
        //fetch the product by Given Id
        //  convert dto to entity
        //  set the id since updating the ID  record
        //  SAve and return DTO
        return productRepository.findById(id)
                .flatMap(p -> dtoMono.map(EntityUtils:: dtotoEntity)
                        .doOnNext(s -> s.setId(id)))
                .flatMap(productRepository:: save)
                .map(EntityUtils::entitytoDTO);
    }

    public Mono<Void> deleteProduct(String id){
        return productRepository.deleteById(id);
    }
}
