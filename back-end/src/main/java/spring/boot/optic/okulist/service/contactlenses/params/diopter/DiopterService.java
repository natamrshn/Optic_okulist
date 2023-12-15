package spring.boot.optic.okulist.service.contactlenses.params.diopter;

import spring.boot.optic.okulist.dto.contactlenses.parameters.diopter.DiopterRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.diopter.DiopterResponseDto;

import java.util.List;

public interface DiopterService {
    DiopterResponseDto getDiopterById(Long id);

    DiopterResponseDto createDiopter(DiopterRequestDto diopterRequestDto);

    List<DiopterResponseDto> getAllDiopters();
}
