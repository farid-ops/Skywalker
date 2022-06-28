package monckey.chopper.service;

import monckey.chopper.entity.*;
import monckey.chopper.error.ResourceNotFoundException;
import monckey.chopper.repo.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderExtImpl implements OrderExt{

    @PersistenceContext
    private EntityManager entityManager;
    private final AddressRepository addressRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final OrderEntityRepository orderEntityRepository;
    private final OrderItemRepository orderItemRepository;

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
        /*
        * Get all items from database
        * */
        Iterable<Item> itemFromDb = this.itemRepository.findByCustomerId(order.getCustomer().getId());
        /*
        * Collect all item in list by the separator
        * */
        List<Item> items = StreamSupport.stream(itemFromDb.spliterator(), false).collect(Collectors.toList());

        /*
        * verified if user Id have or not a cart
        * */
        if (items.size()<1)
            throw new ResourceNotFoundException(String.format("There is no item found for this user ID (%s) cart ",order.getCustomer().getId()));

        /*
        * Get total order price
        * */
        BigDecimal total = BigDecimal.ZERO;
        for (Item item:items
             ) {
            total=BigDecimal.valueOf(item.getQuantity()).multiply(item.getPrice()).add(total);
        }

        /*
        * Now i create order
        * */
        Timestamp orderDate = Timestamp.from(Instant.now());
        this.entityManager.createNativeQuery("""
        INSERT INTO tijara.orders(customer_id, address_id, card_id, status, total, order_date)
        VALUES(?,?,?,?,?)
        """)
                .setParameter(1, order.getCustomer().getId())
                .setParameter(2, order.getAddress().getId())
                .setParameter(3, order.getCard().getId())
                .setParameter(4, Status.CREATED)
                .setParameter(5, total)
                .setParameter(6, orderDate)
                .executeUpdate();

        /*
        * je verifie voir si l'utilisateur a deja un panier
        * */
        Cart cart = this.cartRepository.findCartByCustomerId(order.getCustomer().getId());

        if (Objects.isNull(cart))
            throw new ResourceNotFoundException(String.format("Cart not found for given customer (ID: %s)", order.getCustomer().getId()));

        this.itemRepository.deleteCartItemById(cart.getItems().stream().map(Item::getId).collect(Collectors.toList()), cart.getId());

        OrderEntity orderEntity = (OrderEntity) this.entityManager.createNativeQuery("""
        SELECT o.* FROM tijara.orders o WHERE o.customer_id =? AND o.order_date >=?
        """, OrderEntity.class)
                .setParameter(1, order.getCustomer().getId())
                .setParameter(2, OffsetDateTime.ofInstant(orderDate.toInstant(), ZoneId.of("Z")).truncatedTo(
                ChronoUnit.MICROS))
                .getSingleResult();

        this.orderItemRepository.saveAll(cart.getItems().stream().map(item->new OrderItem().setOrderId(orderEntity.getId()).setItemId(item.getId())).collect(Collectors.toList()));

        return Optional.of(orderEntity);
    }
}
