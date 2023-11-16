package spring.boot.optic.okulist.service.contactlenses.params.color;

import spring.boot.optic.okulist.dto.contactlenses.parameters.color.ColorRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.color.ColorResponseDto;

public interface ColorService {
    ColorResponseDto getColorById(Long id);

    ColorResponseDto createColor(ColorRequestDto colorRequestDto);
}
