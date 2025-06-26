package com.mohan.shopping_app.controller;

import com.mohan.shopping_app.exception.ProductAlreadyExists;
import com.mohan.shopping_app.model.Product;
import com.mohan.shopping_app.model.ProductDto;
import com.mohan.shopping_app.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody ProductDto productDto){
        Product savedProduct = productService.addProduct(productDto);
        return new ResponseEntity<>(
                new ProductDto(savedProduct.getProductId(), savedProduct.getName()
                ,savedProduct.getQuantity(), savedProduct.getPrice(), savedProduct.getCreatedAt()),
                HttpStatus.CREATED
        );
    }

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(name = "name", required = false) String name){
        if (name == null) {
            return ResponseEntity.ok(productService.getAllProducts().stream()
                    .map(this::mapToDto).toList());
        } else {
            return ResponseEntity.ok(productService.getByName(name).stream()
                    .map(this::mapToDto).toList());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable String id){
        UUID uuid = UUID.fromString(id);
        Product product = productService.getProductById(uuid);
        return new ResponseEntity<>(mapToDto(product), HttpStatus.OK);
    }

    private ProductDto mapToDto(Product product){
        return new ProductDto(product.getProductId(), product.getName(), product.getQuantity(), product.getPrice(), product.getCreatedAt());
    }
























}
