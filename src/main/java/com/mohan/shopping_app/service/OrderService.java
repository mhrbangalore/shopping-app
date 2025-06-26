package com.mohan.shopping_app.service;

import com.mohan.shopping_app.exception.ProductNotFound;
import com.mohan.shopping_app.exception.ProductNotInStock;
import com.mohan.shopping_app.model.Order;
import com.mohan.shopping_app.model.OrderDto;
import com.mohan.shopping_app.model.Product;
import com.mohan.shopping_app.repository.OrderRepository;
import com.mohan.shopping_app.repository.ProductRepository;
import com.mohan.shopping_app.utils.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    @Transactional
    public Order placeOrder(OrderDto orderDto){
        if (orderDto == null){
            throw new IllegalArgumentException("Order value is null");
        }
        Product product = productService.isProductAvailableAndInStock(orderDto.getProductId(), orderDto.getQuantity());
        Order order = new Order();
        order.setOrderStatus(OrderStatus.PLACED);
        order.setPlacedAt(LocalDate.now());
        order.setQuantity(orderDto.getQuantity());
        order.setPrice(product.getPrice().multiply(BigDecimal.valueOf(orderDto.getQuantity())));
        order.setProduct(product);
        productService.reduceProductQuantity(orderDto.getProductId(), orderDto.getQuantity());
        return orderRepository.save(order);
    }





















}
