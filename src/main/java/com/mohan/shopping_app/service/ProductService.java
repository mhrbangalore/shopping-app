package com.mohan.shopping_app.service;

import com.mohan.shopping_app.exception.NoProductsWithName;
import com.mohan.shopping_app.exception.ProductAlreadyExists;
import com.mohan.shopping_app.exception.ProductNotFound;
import com.mohan.shopping_app.model.Product;
import com.mohan.shopping_app.model.ProductDto;
import com.mohan.shopping_app.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(ProductDto inProduct){
        if (checkIfProductByNameExists(inProduct.getName())){
            logger.warn("Duplicate Product, product already exists with ID: {}", productRepository.getByName(inProduct.getName()).get().getProductId());
            throw new ProductAlreadyExists("Product with this name is already present");
        }
        Product product = new Product();
        product.setName(inProduct.getName());
        product.setPrice(inProduct.getPrice());
        product.setQuantity(inProduct.getQuantity());
        product.setCreatedAt(LocalDate.now());
        logger.info("Product is added successfully: {}", inProduct.getName());
        return productRepository.save(product);
    }

    public Product getProductById(UUID uuid){
        return productRepository.getByProductId(uuid)
                .orElseThrow(() -> new ProductNotFound("There is no product with UUID: " + uuid.toString()));
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> getByName(String name){
        List<Product> productsListByName = productRepository.findByNameContainingIgnoreCase(name);
        if (productsListByName.isEmpty()){
            throw new NoProductsWithName("There are no products with name " + name + ". Please search with different name or search all products");
        }
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    private boolean checkIfProductByNameExists(String name){
        return productRepository.getByName(name).isPresent();
    }
























}
