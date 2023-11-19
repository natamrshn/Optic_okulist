package spring.boot.optic.okulist.service.contactlenses.manufacturer;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerResponseDto;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.mapper.contactlenses.ManufacturerMapper;
import spring.boot.optic.okulist.model.lenses.parameters.Manufacturer;
import spring.boot.optic.okulist.repository.lenses.ManufacturerRepository;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerMapper manufacturerMapper;

    @Override
    public ManufacturerResponseDto createManufacturer(
            ManufacturerRequestDto manufacturerRequestDto) {
        return null;
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
