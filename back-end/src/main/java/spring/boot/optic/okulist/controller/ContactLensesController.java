package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.contactlenses.ContactLensesResponseDto;
import spring.boot.optic.okulist.dto.contactlenses.ContactLensesSearchParameter;
import spring.boot.optic.okulist.exception.ContactLensesSearchException;
import spring.boot.optic.okulist.service.contactlenses.DiopterOptionsService;
import spring.boot.optic.okulist.service.searchservice.ContactLensesSearchService;

@RestController
@RequestMapping("/contact-lenses")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Contact lenses controller", description = "Manage filter options for Contact lenses")
public class ContactLensesController {
    private final ContactLensesSearchService searchService;
    private final DiopterOptionsService diopterOptionsService;

    @GetMapping("/available-diopter-options")
    public ResponseEntity<List<String>> getAvailableDiopterOptions() {
        try {
            // Отримати доступні опції для діоптрів за допомогою сервісу
            List<String> availableDiopterOptions = diopterOptionsService
                    .determineAvailableOptions("diopter");
            return ResponseEntity.ok(availableDiopterOptions);
        } catch (Exception e) {
            log.error("An error occurred while fetching available diopter options.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections
                            .singletonList("Error determining available diopter options: "
                                    + e.getMessage()));
        }
    }

    @GetMapping("/available-cylinder-options")
    public ResponseEntity<List<String>> getAvailableCylinderOptions() {
        try {
            // Отримати доступні опції для циліндра за допомогою сервісу
            List<String> availableCylinderOptions = diopterOptionsService
                    .determineAvailableOptions("cylinder");
            return ResponseEntity.ok(availableCylinderOptions);
        } catch (Exception e) {
            log.error("An error occurred while fetching available cylinder options.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections
                            .singletonList("Error determining available cylinder options: "
                                    + e.getMessage()));
        }
    }

    @GetMapping("/available-angle-options")
    public ResponseEntity<List<String>> getAvailableAngleOptions() {
        try {
            // Отримати доступні опції для кута за допомогою сервісу
            List<String> availableAngleOptions = diopterOptionsService
                    .determineAvailableOptions("angle");
            return ResponseEntity.ok(availableAngleOptions);
        } catch (Exception e) {
            log.error("An error occurred while fetching available angle options.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections
                            .singletonList("Error determining available angle options: "
                                    + e.getMessage()));
        }
    }

    @GetMapping("/available-curve-options")
    public ResponseEntity<List<String>> getAvailableCurveOptions() {
        try {
            // Отримати доступні опції для кута за допомогою сервісу
            List<String> availableCurveOptions = diopterOptionsService
                    .determineAvailableOptions("curve");
            return ResponseEntity.ok(availableCurveOptions);
        } catch (Exception e) {
            log.error("An error occurred while fetching available angle options.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList("Error determining available angle options: "
                            + e.getMessage()));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContactLensesResponseDto>> searchContactLenses(
            ContactLensesSearchParameter contactLensesSearchParameter) {
        try {
            List<ContactLensesResponseDto> result = searchService
                    .searchContactLenses(contactLensesSearchParameter);
            return ResponseEntity.ok(result);
        } catch (ContactLensesSearchException e) {
            log.error("An error occurred during contact lenses search.", e);
            throw new ContactLensesSearchException("Error during contact lenses search", e);
        }
    }

    @ExceptionHandler(ContactLensesSearchException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleContactLensesSearchException(
            ContactLensesSearchException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error during contact lenses search: " + e.getMessage());
    }
}

/*
Якщо використовувати List<ContactLensesResponseDto> замість List<String>, то потрібно було
б повертати об'єкти ContactLensesResponseDto,
 які містять більше інформації, аніж просто стрічки. Це залежить від того, що ви хочете отримати
 від цього запиту.
  Якщо вам потрібно повернути просто доступні опції, то List<String> буде досить.
 */
