package monckey.chopper.service;

import monckey.chopper.entity.OrderEntity;
import monckey.chopper.repo.OrderEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderEntityRepository orderEntityRepository;

    public OrderServiceImpl(OrderEntityRepository orderEntityRepository){
        this.orderEntityRepository = orderEntityRepository;
    }

    @Override
    public Optional<OrderEntity> addOrder(OrderEntity order) {
        return Optional.of(this.orderEntityRepository.save(order));
    }

    @Override
    public Iterable<OrderEntity> getOrderByCustomerId(String customerId) {
        return this.orderEntityRepository.findByCustomerId(customerId);
    }

    @Override
    public Optional<OrderEntity> getOrderId(String orderId) {
        return this.orderEntityRepository.findById(UUID.fromString(orderId));
    }
}
