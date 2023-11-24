package spring.boot.optic.okulist.service.product;

import spring.boot.optic.okulist.dto.product.ProductRequestDto;
import spring.boot.optic.okulist.dto.product.ProductResponseDto;
import spring.boot.optic.okulist.dto.product.ProductSearchParameter;

import java.util.List;

public interface ProductService {

    List<ProductResponseDto> searchProductByParameters(ProductSearchParameter searchParameters);

    void deleteById(Long id);

    ProductResponseDto update(Long id, ProductRequestDto productRequestDto);
}
