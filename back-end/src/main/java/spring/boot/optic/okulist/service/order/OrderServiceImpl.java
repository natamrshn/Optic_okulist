package spring.boot.optic.okulist.service.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.boot.optic.okulist.dto.order.AddressDto;
import spring.boot.optic.okulist.dto.order.CreateOrderRequestDto;
import spring.boot.optic.okulist.dto.order.OrderResponseDto;
import spring.boot.optic.okulist.dto.order.UpdateOrderRequestDto;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.mapper.AddressMapper;
import spring.boot.optic.okulist.mapper.OrderItemMapper;
import spring.boot.optic.okulist.mapper.OrderMapper;
import spring.boot.optic.okulist.model.Address;
import spring.boot.optic.okulist.model.Order;
import spring.boot.optic.okulist.model.OrderItem;
import spring.boot.optic.okulist.model.ShoppingCart;
import spring.boot.optic.okulist.model.user.User;
import spring.boot.optic.okulist.repository.OrderRepository;
import spring.boot.optic.okulist.repository.ShoppingCartRepository;
import spring.boot.optic.okulist.service.emailsender.EmailService;
import spring.boot.optic.okulist.service.order.address.AddressService;
import spring.boot.optic.okulist.service.shoppingcart.ShoppingCartManager;
import spring.boot.optic.okulist.service.user.UserService;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserService userService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartManager manager;
    private final OrderItemMapper orderItemMapper;
    private final EmailService emailService;
    private final AddressMapper addressMapper;
    private final AddressService addressService;

    @Override
    public OrderResponseDto update(Long id, UpdateOrderRequestDto requestDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't found order by id: " + id));
        order.setStatus(requestDto.getStatus());
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long orderId, Order.Status status) {
        Order order = getOrderById(orderId);
        order.setStatus(status);
        order = orderRepository.save(order);
        emailService.sendStatusChangeEmail(order.getUser().getEmail(), status);
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderResponseDto> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public List<OrderResponseDto> findByUserEmail(String email) {
        return orderRepository.findAllByUserEmail(email).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public List<OrderResponseDto> findAllByUserPhoneNumber(Long number) {
        return orderRepository.findAllByUserPhoneNumber(number)
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    private Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() ->
                new EntityNotFoundException("can't find order by id: " + orderId));
    }

    @Override
    @Transactional
    public OrderResponseDto addOrder(Long id, CreateOrderRequestDto createOrderRequestDto) {
        User authUser = userService.getUser(createOrderRequestDto.getSessionId());
        ShoppingCart shoppingCart = shoppingCartRepository.getByUserId(authUser.getId())
                .orElseGet(() -> manager.registerNewCart(authUser));
        Order order = new Order();
        order.setManualAddressInput(createOrderRequestDto.isManualAddressInput());
        Address shippingAddress = determineShippingAddress(createOrderRequestDto);
        order.setShippingAddress(shippingAddress.getAddressLine1());
        order.setChosenAddressId(createOrderRequestDto.getChosenAddressId());
        order.setOrderItems(getOrderItemsFromShoppingCart(shoppingCart, order));
        saveOrderForUser(order, authUser);
        /*
     // Initiate Liqpay payment
        try {
            LiqpayPaymentResponse paymentResponse = liqpayService.initiatePayment(order.getId(),
            order.getTotal().toString());
            // Redirect user to Liqpay checkout page using paymentResponse.data
        } catch (Exception e) {
            // Handle payment initiation errors
        }

        return orderMapper.toDto(order);
    }
    */

        manager.clearCart(shoppingCart);

        return orderMapper.toDto(order);
    }

    private Address determineShippingAddress(CreateOrderRequestDto createOrderRequestDto) {
        if (createOrderRequestDto.isManualAddressInput()) {
            Address manualAddress = new Address();
            manualAddress.setAddressLine1(createOrderRequestDto.getShippingAddress());
            return manualAddress;
        } else {
            if (createOrderRequestDto.getChosenAddressId() != null) {
                AddressDto chosenAddressDto = addressService.getById(createOrderRequestDto.getChosenAddressId());
                return addressMapper.toEntity(chosenAddressDto);
            } else {
                AddressDto defaultAddressDto = addressService.getDefaultAddress();
                return addressMapper.toEntity(defaultAddressDto);
            }
        }
    }

    private void saveOrderForUser(Order order, User user) {
        order.setUser(user);
        order.setTotal(calculateTotalPrice(order.getOrderItems()));
        order.setStatus(Order.Status.PENDING);
        order.setOrderDate(LocalDateTime.now());
        orderRepository.save(order);
    }

    private BigDecimal calculateTotalPrice(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItem -> orderItem.getProduct().getPrice()
                        .multiply(new BigDecimal(orderItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Set<OrderItem> getOrderItemsFromShoppingCart(ShoppingCart shoppingCart, Order order) {
        return shoppingCart.getCartItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setOrder(order);
                    orderItem.setPrice(cartItem.getProduct().getPrice()
                            .multiply(new BigDecimal(cartItem.getQuantity())));
                    return orderItem;
                })
                .collect(Collectors.toSet());
    }

    @Override
    public List<OrderResponseDto> getByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId).stream()
                .map(orderMapper::toDto).toList();
    }

    @Override
    public OrderResponseDto findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't found order by id: " + id));
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderResponseDto> findAllOrders(Long id, Pageable pageable) {
        return orderRepository.findAllOrders(id).stream().map(orderMapper::toDto).toList();
    }

    @Override
    public OrderResponseDto getByOrderIdAndOrderItemId(Long orderId, Long orderItemsId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(
                        () -> new EntityNotFoundException("cant find order by provided ID : "
                                + orderId));
        return order.getOrderItems()
                .stream()
                .filter(o -> o.getId().equals(orderItemsId))
                .findFirst()
                .map(orderItemMapper::toItemResponseDto)
                .orElseThrow(
                        () -> new EntityNotFoundException("cant find item using provided ID : "
                                + orderItemsId
                                + ", with order id"
                                + orderId));
    }
}
