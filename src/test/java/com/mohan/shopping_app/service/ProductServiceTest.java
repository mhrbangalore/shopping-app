package com.mohan.shopping_app.service;

import com.mohan.shopping_app.exception.NoProductsWithName;
import com.mohan.shopping_app.exception.ProductAlreadyExists;
import com.mohan.shopping_app.exception.ProductNotFound;
import com.mohan.shopping_app.exception.ProductNotInStock;
import com.mohan.shopping_app.model.Product;
import com.mohan.shopping_app.model.ProductDto;
import com.mohan.shopping_app.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    Product product;
    private ProductDto productDto;

    @BeforeEach
    public void setUp(){
        UUID product_id = UUID.randomUUID();
        LocalDate created_At = LocalDate.now();
        product = new Product(product_id, "Test Product 1", 100, BigDecimal.valueOf(35.00), created_At);
        productDto = new ProductDto(product_id, product.getName(), product.getQuantity(), product.getPrice(), created_At);
    }

    @Test
    public void testThatAddProduct(){
        when(productRepository.save(any(Product.class))).thenReturn(product);
        Product result = productService.addProduct(productDto);
        assertEquals(product.getName(), result.getName());
        verify(productRepository).getByName(product.getName());
        verify(productRepository).save(any(Product.class));
    }

    @Test
    public void testGetProductByIdReturnsProduct(){
        when(productRepository.getByProductId(any(UUID.class))).thenReturn(Optional.of(product));
        Product result = productService.getProductById(UUID.randomUUID());
        assertEquals(product.getName(), result.getName());
    }

    @Test
    public void testGetProductByIdThrowsProductNotFoundException(){
        when(productRepository.getByProductId(any(UUID.class))).thenReturn(Optional.empty());
        UUID random = UUID.randomUUID();
        assertThrows(ProductNotFound.class, () -> productService.getProductById(random));
        verify(productRepository).getByProductId(random);
    }

    @Test
    public void getByNameReturnsProduct(){
        when(productRepository.findByNameContainingIgnoreCase("XYZ")).thenReturn(Collections.singletonList(product));
        List<Product> result = productService.getByName("XYZ");
        assertEquals(product, result.getFirst());
    }

    @Test
    public void testThatGetByNameThrowsNoProductWithName(){
        when(productRepository.findByNameContainingIgnoreCase("XYZ")).thenReturn(Collections.emptyList());
        assertThrows(NoProductsWithName.class, () -> productService.getByName("XYZ"));
        verify(productRepository).findByNameContainingIgnoreCase("XYZ");
    }

    @Test
    public void testThatReturnProductForInStock(){
        when(productRepository.getByProductId(product.getProductId())).thenReturn(Optional.of(product));
        Product result = productService.isProductAvailableAndInStock(product.getProductId(), 99);
        assertEquals(product, result);
    }

    @Test
    public void testThatReturnsProductNotInStock(){
        when(productRepository.getByProductId(product.getProductId())).thenReturn(Optional.of(product));
        assertThrows(ProductNotInStock.class, () -> productService.isProductAvailableAndInStock(product.getProductId(), 199));
        verify(productRepository).getByProductId(product.getProductId());
    }

    @Test
    public void testThatReduceProductQuantity() {
        when(productRepository.getByProductId(product.getProductId())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        productService.reduceProductQuantity(product.getProductId(), 50);
        verify(productRepository).save(argThat(p -> p.getQuantity() == 50));
    }































}
