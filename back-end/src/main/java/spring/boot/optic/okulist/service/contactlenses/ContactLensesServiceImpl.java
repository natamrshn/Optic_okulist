package spring.boot.optic.okulist.service.contactlenses;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses.ContactLensesRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.contactlenses.ContactLensesResponseDto;
import spring.boot.optic.okulist.mapper.contactlenses.ContactLensesMapper;
import spring.boot.optic.okulist.repository.lenses.ContactLensesRepository;

@Service
@RequiredArgsConstructor
public class ContactLensesServiceImpl implements ContactLensesService {

    private final ContactLensesRepository contactLensesRepository;
    private final ContactLensesMapper contactLensesMapper;

    @Override
    public ContactLensesResponseDto createContactLenses(
            ContactLensesRequestDto contactLensesRequestDto) {
        return null;
    }
}
