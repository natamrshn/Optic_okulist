package spring.boot.optic.okulist.service.contactlenses.params.cylinder;

import spring.boot.optic.okulist.dto.contactlenses.parameters.cylinder.CylinderRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.cylinder.CylinderResponseDto;

import java.util.List;

public interface CylinderService {
    CylinderResponseDto getCylinderById(Long id);

    CylinderResponseDto createCylinder(CylinderRequestDto cylinderRequestDto);
    List<CylinderResponseDto> getAllCylinders();
}
