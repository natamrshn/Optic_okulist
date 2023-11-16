package spring.boot.optic.okulist.service.contactlenses;

import spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses.ContactLensesRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses.ContactLensesResponseDto;

public interface ContactLensesService {
    ContactLensesResponseDto createContactLenses(ContactLensesRequestDto contactLensesRequestDto);
}
