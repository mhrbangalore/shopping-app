package com.mohan.shopping_app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mohan.shopping_app.utils.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class OrderDto {

    @JsonProperty("order_id")
    private int orderId;

    @JsonProperty("product_id")
    private UUID productId;

    @JsonProperty("product")
    private ProductDto productDto;

    private String name;

    private int quantity;

    private BigDecimal price;

    @JsonProperty("placed_at")
    private LocalDate placedAt;

    private OrderStatus status;

    public OrderDto(UUID productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
