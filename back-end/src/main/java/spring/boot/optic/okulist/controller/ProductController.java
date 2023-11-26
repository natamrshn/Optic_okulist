package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spring.boot.optic.okulist.dto.product.ProductRequestDto;
import spring.boot.optic.okulist.dto.product.ProductResponseDto;
import spring.boot.optic.okulist.dto.product.ProductSearchParameter;
import spring.boot.optic.okulist.service.product.ProductService;

import java.util.List;

@Tag(name = "Product Controller",
        description = "Endpoints for managing product")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/product")
public class ProductController {
    private static final Logger logger = LogManager.getLogger(ProductController.class);
    private final ProductService productService;

    @Operation(summary = "Update product by ID")
    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id,
                                            @RequestBody ProductRequestDto productRequestDto) {
        logger.info("Updating product with ID: " + id);
        return productService.update(id, productRequestDto);
    }

    @Operation(summary = "Search for product",
            description = "Searches for product in the store based on "
                    + "various search parameters "
    )
    @GetMapping("/search")
    public List<ProductResponseDto> searchProduct(ProductSearchParameter searchParameters) {
        return productService.searchProductByParameters(searchParameters);
    }

    @Operation(summary = "Delete product by their ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(@PathVariable Long id) {
        logger.info("Deleting product with ID: " + id);
        productService.deleteById(id);
    }
}
