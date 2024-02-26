package spring.boot.optic.okulist.service.contactlenses.manufacturer;

import static java.util.stream.Collectors.toSet;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerResponseDto;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.mapper.contactlenses.ManufacturerMapper;
import spring.boot.optic.okulist.model.lenses.parameters.Addition;
import spring.boot.optic.okulist.model.lenses.parameters.Color;
import spring.boot.optic.okulist.model.lenses.parameters.Cylinder;
import spring.boot.optic.okulist.model.lenses.parameters.Degree;
import spring.boot.optic.okulist.model.lenses.parameters.Diopter;
import spring.boot.optic.okulist.model.lenses.parameters.Manufacturer;
import spring.boot.optic.okulist.model.lenses.parameters.Sphere;
import spring.boot.optic.okulist.repository.lenses.ManufacturerRepository;
import spring.boot.optic.okulist.repository.lenses.paramsrepository.AdditionRepository;
import spring.boot.optic.okulist.repository.lenses.paramsrepository.ColorRepository;
import spring.boot.optic.okulist.repository.lenses.paramsrepository.CylinderRepository;
import spring.boot.optic.okulist.repository.lenses.paramsrepository.DegreeRepository;
import spring.boot.optic.okulist.repository.lenses.paramsrepository.DiopterRepository;
import spring.boot.optic.okulist.repository.lenses.paramsrepository.SphereRepository;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerMapper manufacturerMapper;
    private final DegreeRepository degreeRepository;
    private final DiopterRepository diopterRepository;
    private final SphereRepository sphereRepository;
    private final ColorRepository colorRepository;
    private final AdditionRepository additionRepository;
    private final CylinderRepository cylinderRepository;

    @Override
    public ManufacturerResponseDto createManufacturer(
            ManufacturerRequestDto manufacturerRequestDto) {
        Manufacturer manufacturer = manufacturerMapper.toModel(manufacturerRequestDto);
        processColors(manufacturerRequestDto.getColorsIds(), manufacturer);
        processAdditions(manufacturerRequestDto.getAdditionsIds(), manufacturer);
        setCylinder(manufacturerRequestDto.getCylinderId(), manufacturer);
        setDegree(manufacturerRequestDto.getDegreeId(), manufacturer);
        setDiopter(manufacturerRequestDto.getDiopterId(), manufacturer);
        processSpheres(manufacturerRequestDto.getSpheresIds(), manufacturer);
        Manufacturer savedManufacturer = manufacturerRepository.save(manufacturer);
        return manufacturerMapper.toDto(savedManufacturer);
    }

    private void processColors(List<Long> requestedColors, Manufacturer manufacturer) {
        if (requestedColors != null && !requestedColors.isEmpty()) {
            List<Color> colors = findByIdList(colorRepository, requestedColors, Color.class, "Colors");
            manufacturer.setColors(colors);
        }
    }

    private void processAdditions(List<Long> requestedAdditions, Manufacturer manufacturer) {
        if (requestedAdditions != null && !requestedAdditions.isEmpty()) {
            List<Addition> additions =
                    findByIdList(additionRepository, requestedAdditions,
                            Addition.class, "Additions");
            manufacturer.setAdditions(additions);
        }
    }

    private void setCylinder(Long cylinderId, Manufacturer manufacturer) {
        if (cylinderId != null) {
            Cylinder cylinder = findById(cylinderRepository, cylinderId, Cylinder.class, "Cylinder");
            manufacturer.setCylinder(cylinder);
        }
    }

    private void setDegree(Long degreeId, Manufacturer manufacturer) {
        if (degreeId != null) {
            Degree degree = findById(degreeRepository, degreeId, Degree.class, "Degree");
            manufacturer.setDegree(degree);
        }
    }

    private void setDiopter(Long diopterId, Manufacturer manufacturer) {
        if (diopterId != null) {
            Diopter diopter = findById(diopterRepository,
                    diopterId, Diopter.class, "Diopter");
            manufacturer.setDiopter(diopter);
        }
    }

    private void processSpheres(List<Long> requestedSpheres, Manufacturer manufacturer) {
        if (requestedSpheres != null && !requestedSpheres.isEmpty()) {
            List<Sphere> spheres = findByIdList(sphereRepository, requestedSpheres,
                    Sphere.class, "Spheres");
            manufacturer.setSpheres(spheres);
        }
    }

    private <T> List<T> findByIdList(JpaRepository<T, Long> repository,
                                     List<Long> ids, Class<T> entityClass, String entityName) {
        List<T> entities = ids.stream()
                .map(repository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        if (entities.size() != ids.size()) {
            Set<Long> foundIds = entities.stream()
                    .map(entity -> {
                        try {
                            return (Long) entity.getClass().getMethod("getId").invoke(entity);
                        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(toSet());
            List<Long> notFoundIds = ids.stream()
                    .filter(id -> !foundIds.contains(id))
                    .toList();
            throw new EntityNotFoundException("Not all "
                    + entityName + " were found. Haven't found "
                    + entityName + " with ids: " + notFoundIds);
        }
        return entities;
    }

    private <T> T findById(JpaRepository<T, Long> repository,
                           Long id, Class<T> entityClass, String entityName) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(entityClass.getSimpleName()
                        + " not found with ID: " + id));
    }

    @Override
    public ManufacturerResponseDto getManufacturerById(Long manufacturerId) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new EntityNotFoundException("Manufacturer not found with id: " + manufacturerId));
        return manufacturerMapper.toDto(manufacturer);
    }

    @Override
    public List<ManufacturerResponseDto> getAllManufacturers() {
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();
        return manufacturers.stream()
                .map(manufacturerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteManufacturer(Long manufacturerId) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't found liquid with ID :"
                                + manufacturerId)
                );
        manufacturer.setDeleted(true);
        manufacturerRepository.save(manufacturer);
    }
}
