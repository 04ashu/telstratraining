package com.telstra.productservice.service;

import com.telstra.productservice.dto.ProductDTO;
import com.telstra.productservice.entity.Product;
import com.telstra.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ProductDTO getProductById(Long id){
        Product product =  productRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product not found"));

        return new ProductDTO(product.getId(),product.getName(),product.getPrice());
    }

    public ProductDTO createProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        Product savedProduct = productRepository.save(product);

        return new ProductDTO(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getPrice()
        );
    }

    public List<ProductDTO> getAllProducts(){

        return productRepository.findAll()
                .stream()
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getName(),
                        product.getPrice()
                )).toList();
    }
}
