package monckey.chopper.service;

import monckey.chopper.entity.OrderEntity;

import java.util.Optional;

public interface OrderExt {

    Optional<OrderEntity> insert(OrderEntity order);
}
