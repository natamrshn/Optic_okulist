package spring.boot.optic.okulist.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerResponseDto;
import spring.boot.optic.okulist.service.contactlenses.manufacturer.ManufacturerService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    /*
    цей контролер нам потрібен для того щоб створювати виробника.І потім використовувати його при
    створенні нового продукту.

    Продукт можна буде створювати за вробником або за конфігурацією
     */

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ManufacturerResponseDto createManufacturer(
            @RequestBody @Valid ManufacturerRequestDto manufacturerRequestDto) {
        return null;
    }

    @GetMapping
    public List<ManufacturerResponseDto> getAllManufacturers() {
        return null;
    }
}
