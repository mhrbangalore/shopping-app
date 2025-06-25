package com.mohan.shopping_app.config;

import com.mohan.shopping_app.model.Product;
import com.mohan.shopping_app.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedProducts(ProductRepository productRepository) {
        return args -> {

            if (productRepository.count() == 0) {
                Product p1 = new Product(null, "Apple iPhone 15", 10, new BigDecimal("999.99"), LocalDate.now());
                Product p2 = new Product(null, "Samsung Galaxy S24", 15, new BigDecimal("899.00"), LocalDate.now());
                Product p3 = new Product(null, "Sony WH-1000XM5", 30, new BigDecimal("349.99"), LocalDate.now());
                Product p4 = new Product(null, "Dell XPS 13", 5, new BigDecimal("1249.50"), LocalDate.now());
                Product p5 = new Product(null, "Apple AirPods Pro", 25, new BigDecimal("249.00"), LocalDate.now());

                productRepository.saveAll(List.of(p1, p2, p3, p4, p5));

                System.out.println("✅ Sample products seeded to H2 database");
            } else {
                System.out.println("ℹ️ Products already exist in database — skipping seeding.");
            }
        };
    }

}
