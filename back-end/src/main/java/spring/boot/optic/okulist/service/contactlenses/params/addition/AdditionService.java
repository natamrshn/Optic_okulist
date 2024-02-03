package spring.boot.optic.okulist.service.contactlenses.params.addition;

import spring.boot.optic.okulist.dto.contactlenses.parameters.addition.AdditionRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.addition.AdditionResponseDto;

import java.util.List;

public interface AdditionService {
    AdditionResponseDto getAdditionById(Long id);

    AdditionResponseDto createAddition(AdditionRequestDto additionRequestDto);

    List<AdditionResponseDto> getAllAdditions();
}
