package com.example.demo.service;

import com.example.demo.dto.ProductResponse;

import org.springframework.stereotype.Service;

import com.example.demo.repository.ProductRepository;
import com.example.demo.entity.Product;

import java.math.BigDecimal;

import java.util.List;

@Service

public class ProductService {

    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAllProducts(){
        if(this.productRepository.count()== 0){
            this.productRepository.save(new Product("MacBook Pro", new BigDecimal("2000.00")));
            this.productRepository.save(new Product("Bàn phím cơ", new BigDecimal("150.00")));
        }
        return this.productRepository.findAll().stream().map(product -> new ProductResponse(product.getId(), product.getName(), product.getPrice())).toList();
    }
}
