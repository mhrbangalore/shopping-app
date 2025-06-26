package com.mohan.shopping_app.controller;

import com.mohan.shopping_app.model.Order;
import com.mohan.shopping_app.model.OrderDto;
import com.mohan.shopping_app.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<OrderDto> placeOrder(@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(mapToOrder(orderService.placeOrder(orderDto)), HttpStatus.CREATED);
    }

    private OrderDto mapToOrder(Order order){
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .productId(order.getProduct().getProductId())
                .price(order.getPrice())
                .name(order.getProduct().getName())
                .placedAt(order.getPlacedAt())
                .quantity(order.getQuantity())
                .productDto(null)
                .status(order.getOrderStatus())
                .build();
    }

























}
