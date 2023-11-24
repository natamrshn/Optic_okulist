package spring.boot.optic.okulist.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.product.ProductRequestDto;
import spring.boot.optic.okulist.dto.product.ProductResponseDto;
import spring.boot.optic.okulist.dto.product.ProductSearchParameter;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.mapper.ProductMapper;
import spring.boot.optic.okulist.model.Glasses;
import spring.boot.optic.okulist.model.Product;
import spring.boot.optic.okulist.repository.ProductRepository;
import spring.boot.optic.okulist.specification.product.builders.ProductSpecificationBuilder;

import java.util.List;

import static spring.boot.optic.okulist.service.liquid.LiquidServiceImpl.getStrings;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductSpecificationBuilder productSpecificationBuilder;

    @Override
    public List<ProductResponseDto> searchProductByParameters(
        ProductSearchParameter searchParameters) {
            Specification<Product> productSpecification = productSpecificationBuilder
                    .build(searchParameters);
            return productRepository.findAll(productSpecification)
                    .stream()
                    .map(productMapper::toDto)
                    .toList();
    }

    @Override
    public void deleteById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't found product with ID :" + id)
        );
        product.setDeleted(true);
        productRepository.save(product);
    }

    @Override
    public ProductResponseDto update(Long id, ProductRequestDto productRequestDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find product with ID: " + id));
        BeanUtils.copyProperties(productRequestDto, existingProduct,
                getNullPropertyNames(productRequestDto));
        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDto(updatedProduct);
    }

    private String[] getNullPropertyNames(Object source) {
        return getStrings(source);
    }
}
