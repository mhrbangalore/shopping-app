package com.mohan.shopping_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @JsonProperty(namespace = "product_id")
    private UUID id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Min(value = 1, message = "Value cannot be less than 1")
    private int quantity;

    private BigDecimal price;

    @JsonProperty(namespace = "created_at")
    private LocalDate createdAt;

    public ProductDto(String name, int quantity, BigDecimal price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
