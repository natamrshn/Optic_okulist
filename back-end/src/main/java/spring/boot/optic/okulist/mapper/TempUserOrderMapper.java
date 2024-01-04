package spring.boot.optic.okulist.mapper;

import org.mapstruct.Mapper;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.order.CreateOrderRequestDto;
import spring.boot.optic.okulist.model.Order;

@Mapper(config = MapperConfig.class)
public interface TempUserOrderMapper {
   // @Mapping(target = "shippingAddress", source = "shippingAddress")
    Order toModel(CreateOrderRequestDto createOrderRequestDto);
}
