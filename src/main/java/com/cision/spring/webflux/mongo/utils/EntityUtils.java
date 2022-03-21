package com.cision.spring.webflux.mongo.utils;

import com.cision.spring.webflux.mongo.dto.ProductDTO;
import com.cision.spring.webflux.mongo.entity.Product;
import org.springframework.beans.BeanUtils;

public class EntityUtils {

    public static ProductDTO entitytoDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        //Use Beanutils only if two class have same properties
        BeanUtils.copyProperties(product, productDTO);
        return productDTO;
    }

    public static Product dtotoEntity(ProductDTO productDTO){
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return product;
    }
}
