package spring.boot.optic.okulist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.order.CreateOrderRequestDto;
import spring.boot.optic.okulist.dto.order.OrderResponseDto;
import spring.boot.optic.okulist.model.Order;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {
    OrderResponseDto toDto(Order order);

    @Mapping(target = "id", ignore = true)
    Order toModel(CreateOrderRequestDto orderRequestDto);
}
