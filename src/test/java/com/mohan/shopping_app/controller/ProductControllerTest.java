package com.mohan.shopping_app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mohan.shopping_app.model.Product;
import com.mohan.shopping_app.model.ProductDto;
import com.mohan.shopping_app.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    private UUID productId;
    private Product product;
    private ProductDto productDto;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        productId = UUID.randomUUID();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        product = new Product(productId, "Test Product 1", 100, BigDecimal.valueOf(200), LocalDate.of(2025, 1, 1));
        productDto = new ProductDto(productId, "Test Product 1", 100, BigDecimal.valueOf(200), LocalDate.of(2025, 1, 1));
    }

    @Test
    public void testAddProduct_Success() throws Exception {
        when(productService.addProduct(any(ProductDto.class))).thenReturn(product);
        mockMvc.perform(
                post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto))
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Product 1"));
    }


























}
