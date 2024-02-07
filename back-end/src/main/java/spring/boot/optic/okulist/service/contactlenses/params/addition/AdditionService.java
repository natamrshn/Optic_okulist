package spring.boot.optic.okulist.service.contactlenses.params.addition;

import java.util.List;
import spring.boot.optic.okulist.dto.contactlenses.parameters.addition.AdditionRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.addition.AdditionResponseDto;

public interface AdditionService {
    AdditionResponseDto getAdditionById(Long id);

    AdditionResponseDto createAddition(AdditionRequestDto additionRequestDto);

    List<AdditionResponseDto> getAllAdditions();
}
