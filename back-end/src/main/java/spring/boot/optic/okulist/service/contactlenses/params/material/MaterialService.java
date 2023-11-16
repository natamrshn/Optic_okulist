package spring.boot.optic.okulist.service.contactlenses.params.material;

import spring.boot.optic.okulist.dto.contactlenses.parameters.material.MaterialRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.material.MaterialResponseDto;

public interface MaterialService {
    MaterialResponseDto getMaterialById(Long id);

    MaterialResponseDto createMaterial(MaterialRequestDto materialRequestDto);
}
