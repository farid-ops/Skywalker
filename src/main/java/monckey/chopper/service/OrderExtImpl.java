package monckey.chopper.service;

import monckey.chopper.entity.Cart;
import monckey.chopper.entity.Item;
import monckey.chopper.entity.OrderEntity;
import monckey.chopper.entity.Status;
import monckey.chopper.error.ResourceNotFoundException;
import monckey.chopper.repo.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class OrderExtImpl implements OrderExt{

    @PersistenceContext
    private EntityManager entityManager;
    private AddressRepository addressRepository;
    private ItemRepository itemRepository;
    private CartRepository cartRepository;
    private OrderEntityRepository orderEntityRepository;
    private OrderItemRepository orderItemRepository;

    public OrderExtImpl(AddressRepository addressRepository,
                        ItemRepository itemRepository,
                        CartRepository cartRepository,
                        OrderEntityRepository orderEntityRepository,
                        OrderItemRepository orderItemRepository) {
        this.addressRepository = addressRepository;
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.orderEntityRepository = orderEntityRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Optional<OrderEntity> insert(OrderEntity order) {
        Iterable<Item> itemdb = itemRepository.findByCustomerId(String.valueOf(order.getUser().getId()));
        List<Item> items = StreamSupport.stream(itemdb.spliterator(), false).collect(Collectors.toList());

        if (items.size() < 1)
            throw new ResourceNotFoundException(String.format("There is no item found", order.getUser().getId()));

        BigDecimal total = BigDecimal.ZERO;

        for (Item i: items) {
            total = (BigDecimal.valueOf(i.getQuantity()).multiply(i.getPrice())).add(total);
        }

        Timestamp date = Timestamp.from(Instant.now());

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setAddress(order.getAddress());
        orderEntity.setOrderDate(date);
        orderEntity.setCard(order.getCard());
        orderEntity.setTotale(total);
        orderEntity.setStatus(Status.CREATED);

        this.orderEntityRepository.save(orderEntity);

        Optional<Cart> optionalCart = Optional.ofNullable(this.cartRepository.findCartByCustomerId(order.getUser().getId()));
        Cart cart = optionalCart.orElseThrow(()-> new ResourceNotFoundException(String.format("Cart not found for given customer (:ID)%s", order.getUser().getId())));

        this.entityManager.createQuery("select o from OrderEntity o ");
        return Optional.empty();
    }
}
