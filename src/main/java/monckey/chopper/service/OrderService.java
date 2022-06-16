package monckey.chopper.service;

import monckey.chopper.entity.OrderEntity;

import javax.validation.Valid;
import java.util.Optional;

public interface OrderService {
    Optional<OrderEntity> addOrder(@Valid OrderEntity order);

    Iterable<OrderEntity> getOrderByCustomerId(String customerId);

    Optional<OrderEntity> getOrderId(String orderId);
}
