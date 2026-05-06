package com.telstra.productservice.controller;

import com.telstra.productservice.dto.ProductDTO;
import com.telstra.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createproduct(@RequestBody ProductDTO productDTO){
        ProductDTO savedProduct = productService.createProduct(productDTO);

        return  new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> products = productService.getAllProducts();

        if(products.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }
}
