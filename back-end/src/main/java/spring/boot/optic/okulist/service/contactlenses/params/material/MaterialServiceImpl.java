package spring.boot.optic.okulist.service.contactlenses.params.material;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.contactlenses.parameters.material.MaterialRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.material.MaterialResponseDto;
import spring.boot.optic.okulist.mapper.contactlenses.MaterialMapper;
import spring.boot.optic.okulist.model.lenses.parameters.Material;
import spring.boot.optic.okulist.repository.lenses.paramsrepository.MaterialRepository;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    @Override
    public MaterialResponseDto createMaterial(MaterialRequestDto materialRequestDto) {
        Material material = materialMapper.toModel(materialRequestDto);
        Material savedMaterial = materialRepository.save(material);
        return materialMapper.toDto(savedMaterial);
    }

    @Override
    public MaterialResponseDto getMaterialById(Long id) {
        Material material = materialRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Material not found with id: "
                                + id));
        return materialMapper.toDto(material);
    }

}
