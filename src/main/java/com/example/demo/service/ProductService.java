package com.example.demo.service;

import com.example.demo.dto.ProductResponse;

import org.springframework.stereotype.Service;

import com.example.demo.repository.ProductRepository;
import com.example.demo.entity.Product;

import com.example.demo.dto.CreateProductRequest;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.List;

import org.springframework.web.client.RestClient;


@Service

public class ProductService {

    private final ProductRepository productRepository;
    private final RestClient restClient;
    public ProductService(ProductRepository productRepository, RestClient restClient){
        this.productRepository = productRepository;
        this.restClient = restClient;
    }

    public List<ProductResponse> getAllProducts(){
        if(this.productRepository.count()== 0){
            this.productRepository.save(new Product("MacBook Pro", new BigDecimal("2000.00")));
            this.productRepository.save(new Product("Bàn phím cơ", new BigDecimal("150.00")));
        }
        return this.productRepository.findAll().stream().map(product -> new ProductResponse(product.getId(), product.getName(), product.getPrice())).toList();
    }

    public ProductResponse getProductById(Long id){
       Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm với ID: " + id));
       return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }

    @Transactional
    public ProductResponse createProduct(CreateProductRequest request){
       Product product = new Product(request.name(), request.price());
       Product saveProduct = productRepository.save(product);
       return new ProductResponse(saveProduct.getId(), saveProduct.getName(), saveProduct.getPrice());
    }

    public String checkExternalServiceStatus(){
        return restClient.get().uri("https://jsonplaceholder.typicode.com/posts/1").retrieve().body(String.class);
    }

}
