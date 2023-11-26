package spring.boot.optic.okulist.service.contactlenses;

import org.springframework.data.domain.Pageable;
import spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses.ContactLensesRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses.ContactLensesResponseDto;
import spring.boot.optic.okulist.dto.glasses.GlassesRequestDto;
import spring.boot.optic.okulist.dto.glasses.GlassesResponseDto;

import java.util.List;

public interface ContactLensesService {
    ContactLensesResponseDto createContactLenses(ContactLensesRequestDto contactLensesRequestDto);

    List<ContactLensesResponseDto> findAll(Pageable pageable);

    ContactLensesResponseDto getById(Long id);

    ContactLensesResponseDto update(Long id, ContactLensesRequestDto contactLensesRequestDto);

    void deleteLensesById(Long lensesId);
}
