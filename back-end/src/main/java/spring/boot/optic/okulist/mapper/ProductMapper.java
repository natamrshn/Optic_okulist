package spring.boot.optic.okulist.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spring.boot.optic.okulist.config.MapperConfig;
import spring.boot.optic.okulist.dto.product.ProductRequestDto;
import spring.boot.optic.okulist.dto.product.ProductResponseDto;
import spring.boot.optic.okulist.model.Product;

@Mapper(config = MapperConfig.class)
public interface ProductMapper {
    @Mapping(target = "categories", ignore = true)
    ProductResponseDto toDto(Product product);

    Product toModel(ProductRequestDto productRequestDto);

}
