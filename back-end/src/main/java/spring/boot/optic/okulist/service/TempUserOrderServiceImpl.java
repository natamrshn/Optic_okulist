package spring.boot.optic.okulist.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.TempOrderRequestDto;
import spring.boot.optic.okulist.dto.TempOrderResponseDto;
import spring.boot.optic.okulist.exception.InvalidOrderRequestException;
import spring.boot.optic.okulist.mapper.TempUserOrderMapper;
import spring.boot.optic.okulist.model.Order;
import spring.boot.optic.okulist.model.Product;
import spring.boot.optic.okulist.model.TemporaryUser;
import spring.boot.optic.okulist.repository.ProductRepository;
import spring.boot.optic.okulist.repository.TemporaryUserRepository;
import spring.boot.optic.okulist.service.emailsender.EmailService;

@Service
@RequiredArgsConstructor
public class TempUserOrderServiceImpl implements TempUserOrderService {
    private final TemporaryUserRepository userRepository;
    private final ProductRepository productRepository;
    private final TempUserOrderMapper mapper;
    private final EmailService emailService;

    @Override
    public TempOrderResponseDto processOrder(TempOrderRequestDto orderRequest) {
        if (orderRequest.getEmail() == null || orderRequest.getEmail().isEmpty()) {
            throw new InvalidOrderRequestException("Email address is required");
        }
        if (orderRequest.getProducts() == null || orderRequest.getProducts().isEmpty()) {
            throw new InvalidOrderRequestException("At least one product is required");
        }
        String email = orderRequest.getEmail();
        // Send an email notification
        emailService.sendOrderProcessingEmail(email, Order.Status.PROCESSING);
        TemporaryUser temporaryUser = mapper.toEntity(orderRequest);
        List<Product> products = orderRequest.getProducts().stream()
                .map(productRequestDto -> productRepository
                        .findById(productRequestDto
                                .getId()).get())
                .collect(Collectors.toList());
        temporaryUser.setProducts(products);
        TemporaryUser savedUser = userRepository.save(temporaryUser);
        return mapper.toResponseDto(savedUser);
    }
}
