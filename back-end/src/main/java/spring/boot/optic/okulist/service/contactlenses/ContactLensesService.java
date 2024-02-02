package spring.boot.optic.okulist.service.contactlenses;

import java.util.List;
import org.springframework.data.domain.Pageable;
import spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses.ContactLensesRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses.ContactLensesResponseDto;
import spring.boot.optic.okulist.dto.glasses.GlassesResponseDto;

public interface ContactLensesService {
    ContactLensesResponseDto createContactLenses(ContactLensesRequestDto contactLensesRequestDto);

    List<ContactLensesResponseDto> findAll(Pageable pageable);

    ContactLensesResponseDto getById(Long id);

    ContactLensesResponseDto update(Long id, ContactLensesRequestDto contactLensesRequestDto);

    void deleteLensesById(Long lensesId);

    ContactLensesResponseDto findByIdentifier(String identifier);
}
