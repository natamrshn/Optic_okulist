package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spring.boot.optic.okulist.dto.glasses.GlassesRequestDto;
import spring.boot.optic.okulist.dto.glasses.GlassesResponseDto;
import spring.boot.optic.okulist.service.glasses.GlassesService;

import java.util.List;

@Tag(name = "Glasses Controller",
        description = "Endpoints for managing glasses")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/glasses")
public class GlassesController {
    private static final Logger logger = LogManager.getLogger(GlassesController.class);
    private final GlassesService glassesService;

    @Operation(summary = "Create a Glasses",
            description = "Creates a new Glasses in shop list.")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public GlassesResponseDto creatGlasses(@RequestBody
                                              @Valid GlassesRequestDto glassesRequestDto) {
        logger.info("Creating a new glasses.");
        return glassesService.save(glassesRequestDto);
    }

    @Operation(summary = "Get All glasses ")
    @GetMapping
    public List<GlassesResponseDto> getAll(Pageable pageable) {
        return glassesService.findAll(pageable);
    }
// думаю нужно использовать нейм и оно у нас дублируеться
    @Operation(summary = "Get Glasses by ID")
    @GetMapping("/{id}")
    public GlassesResponseDto getGlassesById(@PathVariable Long id) {
        logger.info("Retrieving category with ID: " + id);
        return glassesService.getById(id);
    }

//    @Operation(summary = "Update a Glasses by its ID")
//    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public GlassesResponseDto updateGlasses(@PathVariable Long id,
//                                              @RequestBody GlassesRequestDto glassesRequestDto) {
//        logger.info("Updating category with ID: " + id);
//        return glassesService.update(id, glassesRequestDto);
//    }

    @Operation(summary = "Delete glasses by their ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteGlasses(@PathVariable Long id) {
        logger.info("Deleting glasses with ID: " + id);
        glassesService.deleteById(id);
    }
}
