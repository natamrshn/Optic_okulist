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
        Color color = colorRepository.findById(manufacturerRequestDto.getColorId())
                .orElseThrow(() -> new EntityNotFoundException("Color not found with ID: "
                        + manufacturerRequestDto.getColorId()));
        Cylinder cylinder = cylinderRepository.findById(manufacturerRequestDto.getCylinderId())
                .orElseThrow(() -> new EntityNotFoundException("Cylinder not found with ID: "
                        + manufacturerRequestDto.getCylinderId()));
        Degree degree = degreeRepository.findById(manufacturerRequestDto.getDegreeId())
                .orElseThrow(() -> new EntityNotFoundException("Degree not found with ID: "
                        + manufacturerRequestDto.getDegreeId()));
        Diopter diopter = diopterRepository.findById(manufacturerRequestDto.getDiopterId())
                .orElseThrow(() -> new EntityNotFoundException("Diopter not found with ID: "
                        + manufacturerRequestDto.getDiopterId()));
        Sphere sphere = sphereRepository.findById(manufacturerRequestDto.getSphereId())
                .orElseThrow(() -> new EntityNotFoundException("Sphere not found with ID: "
                        + manufacturerRequestDto.getSphereId()));
        manufacturer.setColor(color);
        manufacturer.setCylinder(cylinder);
        manufacturer.setDegree(degree);
        manufacturer.setDiopter(diopter);
        manufacturer.setSphere(sphere);
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
