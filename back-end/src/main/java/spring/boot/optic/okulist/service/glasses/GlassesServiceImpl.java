package spring.boot.optic.okulist.service.glasses;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.glasses.GlassesRequestDto;
import spring.boot.optic.okulist.dto.glasses.GlassesResponseDto;
import spring.boot.optic.okulist.dto.glasses.GlassesSearchParameter;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.mapper.GlassesMapper;
import spring.boot.optic.okulist.model.Glasses;
import spring.boot.optic.okulist.repository.GlassesRepository;
import spring.boot.optic.okulist.specification.glasses.builders.GlassesSpecificationBuilder;

@Service
@RequiredArgsConstructor
public class GlassesServiceImpl implements GlassesService {
    private final GlassesRepository glassesRepository;
    private final GlassesMapper glassesMapper;
    private final GlassesSpecificationBuilder glassesSpecificationBuilder;

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
                () -> new EntityNotFoundException("Can't found glasses with ID :" + id)
        );
        glasses.setDeleted(true);
        glassesRepository.save(glasses);
    }

    @Override
    public List<GlassesResponseDto> searchGlassesByParameters(
            GlassesSearchParameter searchParameters) {
        Specification<Glasses> glassSpecification = glassesSpecificationBuilder
                .build(searchParameters);
        return glassesRepository.findAll(glassSpecification)
                .stream()
                .map(glassesMapper::toDto)
                .toList();
    }

    @Override
    public GlassesResponseDto update(Long id, GlassesRequestDto glassesRequestDto) {
        Glasses existingGlasses = glassesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find Glasses with ID: " + id));
        BeanUtils.copyProperties(glassesRequestDto, existingGlasses,
                getNullPropertyNames(glassesRequestDto));
        Glasses updatedGlasses = glassesRepository.save(existingGlasses);
        return glassesMapper.toDto(updatedGlasses);
    }

    // Допоміжний метод для отримання імен властивостей з null значеннями
    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
