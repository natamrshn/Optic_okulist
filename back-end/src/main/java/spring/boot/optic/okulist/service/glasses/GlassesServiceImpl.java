package spring.boot.optic.okulist.service.glasses;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.glasses.GlassesRequestDto;
import spring.boot.optic.okulist.dto.glasses.GlassesResponseDto;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.mapper.GlassesMapper;
import spring.boot.optic.okulist.model.Glasses;
import spring.boot.optic.okulist.repository.GlassesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GlassesServiceImpl implements GlassesService {
    private final GlassesRepository glassesRepository;
    private final GlassesMapper glassesMapper;

    @Override
    public List<GlassesResponseDto> findAll(Pageable pageable) {
            return glassesRepository.findAll()
                    .stream()
                    .map(glassesMapper::toDto)
                    .toList();
    }

    @Override
    public GlassesResponseDto getById(Long id) {
        Glasses glasses = glassesRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't found Glasses with ID: " + id)
        );
        return glassesMapper.toDto(glasses);
    }

    @Override
    public GlassesResponseDto save(GlassesRequestDto glassesRequestDto) {
        Glasses glasses = glassesMapper.toModel(glassesRequestDto);
        return glassesMapper.toDto(glassesRepository.save(glasses));
    }

    @Override
    public void deleteById(Long id) {
        Glasses glasses = glassesRepository.findById(id).orElseThrow(
                () ->new EntityNotFoundException("Can't found glasses with ID :" + id)
        );
        glasses.setDeleted(true);
        glassesRepository.save(glasses);
    }

    @Override
    public List<GlassesResponseDto> findSimilar(GlassesRequestDto glassesRequestDto) {
        Glasses referenceGlasses = glassesMapper.toModel(glassesRequestDto);

        List<Glasses> similarGlasses = glassesRepository.findByColorIgnoreCaseAndNameAndPriceAndIdentifierAndDescription(
                referenceGlasses.getColor(),
                referenceGlasses.getName(),
                referenceGlasses.getPrice(),
                referenceGlasses.getIdentifier(),
                referenceGlasses.getDescription()
        );

        return similarGlasses.stream()
                .map(glassesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GlassesResponseDto update(Long id, GlassesRequestDto glassesRequestDto) {
        Glasses existingGlasses = glassesRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find Glasses with ID: " + id)
        );

        Glasses updatedGlasses = glassesMapper.toModel(glassesRequestDto);
        updatedGlasses.setId(existingGlasses.getId());

        return glassesMapper.toDto(glassesRepository.save(updatedGlasses));
    }
}
