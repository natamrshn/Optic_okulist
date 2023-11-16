package spring.boot.optic.okulist.service.contactlenses.params.degree;

import spring.boot.optic.okulist.dto.contactlenses.parameters.degree.DegreeRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.degree.DegreeResponseDto;

public interface DegreeService {
    DegreeResponseDto getDegreeById(Long id);

    DegreeResponseDto createDegree(DegreeRequestDto degreeRequestDto);
}
