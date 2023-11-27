package spring.boot.optic.okulist.service.contactlenses.manufacturer;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerResponseDto;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.mapper.contactlenses.ManufacturerMapper;
import spring.boot.optic.okulist.model.lenses.parameters.Color;
import spring.boot.optic.okulist.model.lenses.parameters.Cylinder;
import spring.boot.optic.okulist.model.lenses.parameters.Degree;
import spring.boot.optic.okulist.model.lenses.parameters.Diopter;
import spring.boot.optic.okulist.model.lenses.parameters.Manufacturer;
import spring.boot.optic.okulist.model.lenses.parameters.Sphere;
import spring.boot.optic.okulist.repository.lenses.ManufacturerRepository;
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
    private final CylinderRepository cylinderRepository;

    @Override
    public ManufacturerResponseDto createManufacturer(
            ManufacturerRequestDto manufacturerRequestDto) {
        Manufacturer manufacturer = manufacturerMapper.toModel(manufacturerRequestDto);
        if (manufacturerRequestDto.getColorId() != null) {
            Color color = colorRepository.findById(manufacturerRequestDto.getColorId())
                    .orElseThrow(() -> new EntityNotFoundException("Color not found with ID: "
                            + manufacturerRequestDto.getColorId()));
            manufacturer.setColor(color);
        }

        if (manufacturerRequestDto.getCylinderId() != null) {
            Cylinder cylinder = cylinderRepository.findById(manufacturerRequestDto.getCylinderId())
                    .orElseThrow(() -> new EntityNotFoundException("Cylinder not found with ID: "
                            + manufacturerRequestDto.getCylinderId()));
            manufacturer.setCylinder(cylinder);
        }
        if (manufacturerRequestDto.getDegreeId() != null) {
            Degree degree = degreeRepository.findById(manufacturerRequestDto.getDegreeId())
                    .orElseThrow(() -> new EntityNotFoundException("Degree not found with ID: "
                            + manufacturerRequestDto.getDegreeId()));
            manufacturer.setDegree(degree);
        }

        if (manufacturerRequestDto.getDiopterId() != null) {
            Diopter diopter = diopterRepository.findById(manufacturerRequestDto.getDiopterId())
                    .orElseThrow(() -> new EntityNotFoundException("Diopter not found with ID: "
                            + manufacturerRequestDto.getDiopterId()));
            manufacturer.setDiopter(diopter);
        }

        if (manufacturerRequestDto.getSphereId() != null) {
            Sphere sphere = sphereRepository.findById(manufacturerRequestDto.getSphereId())
                    .orElseThrow(() -> new EntityNotFoundException("Sphere not found with ID: "
                            + manufacturerRequestDto.getSphereId()));
            manufacturer.setSphere(sphere);
        }
        Manufacturer savedManufacturer = manufacturerRepository.save(manufacturer);
        return manufacturerMapper.toDto(savedManufacturer);
    }

    @Override
    public ManufacturerResponseDto getManufacturerById(Long manufacturerId) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new RuntimeException("Manufacturer not found with id: "
                        + manufacturerId));
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
