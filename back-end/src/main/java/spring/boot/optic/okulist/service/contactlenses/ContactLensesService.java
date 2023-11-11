package spring.boot.optic.okulist.service.contactlenses;

import java.util.List;
import spring.boot.optic.okulist.dto.contactlenses.ContactLensesRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.ContactLensesResponseDto;
import spring.boot.optic.okulist.dto.contactlenses.ContactLensesSearchParameter;

public interface ContactLensesService {
    ContactLensesResponseDto saveContactLenses(ContactLensesRequestDto requestDto);

    List<ContactLensesResponseDto> findAllContactLenses();

    List<ContactLensesResponseDto> getContactLensesByName(String name);

    List<ContactLensesResponseDto> getContactLensesByParameters(
            ContactLensesSearchParameter parameters);
}
