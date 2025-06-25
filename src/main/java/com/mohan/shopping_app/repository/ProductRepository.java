package com.mohan.shopping_app.repository;

import com.mohan.shopping_app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> getByProductId(UUID id);

    List<Product> findAll();

    List<Product> findByNameContainingIgnoreCase(String name);

    Optional<Product> getByName(String name);
}
