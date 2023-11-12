package spring.boot.optic.okulist.service.liquid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.liquid.LiquidRequestDto;
import spring.boot.optic.okulist.dto.liquid.LiquidResponseDto;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.mapper.LiquidMapper;
import spring.boot.optic.okulist.model.Liquid;
import spring.boot.optic.okulist.repository.LiquidRepository;

@Service
@RequiredArgsConstructor
public class LiquidServiceImpl implements LiquidService {
    private final LiquidRepository liquidRepository;
    private final LiquidMapper liquidMapper;
    @Override
    public List<LiquidResponseDto> findAll(Pageable pageable) {
        return liquidRepository.findAll()
                .stream()
                .map(liquidMapper::toDto)
                .toList();
    }

    @Override
    public LiquidResponseDto getById(Long id) {
        Liquid liquid = liquidRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't found Liquid with ID: " + id)
        );
        return liquidMapper.toDto(liquid);
    }

    @Override
    public LiquidResponseDto save(LiquidRequestDto liquidRequestDto) {
        Liquid liquid = liquidMapper.toModel(liquidRequestDto);
        return liquidMapper.toDto(liquidRepository.save(liquid));
    }

    @Override
    public void deleteById(Long id) {
        Liquid liquid = liquidRepository.findById(id).orElseThrow(
                () ->new EntityNotFoundException("Can't found liquid with ID :" + id)
        );
        liquid.setDeleted(true);
        liquidRepository.save(liquid);
    }

    @Override
    public List<LiquidResponseDto> findSimilar(LiquidRequestDto liquidRequestDto) {
        Liquid referenceLiquid = liquidMapper.toModel(liquidRequestDto);

        List<Liquid> similarLiquids = liquidRepository.findByVolumeNotAndPriceNotAndNameAndIdentifierAndDescription(
                referenceLiquid.getVolume(),
                referenceLiquid.getPrice(),
                referenceLiquid.getName(),
                referenceLiquid.getIdentifier(),
                referenceLiquid.getDescription()
        );

        return similarLiquids.stream()
                .map(liquidMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LiquidResponseDto update(Long id, LiquidRequestDto liquidRequestDto) {
        Optional<Liquid> optionalLiquid = liquidRepository.findById(id);

        if (optionalLiquid.isPresent()) {
            Liquid existingLiquid = optionalLiquid.get();

            existingLiquid.setVolume(liquidRequestDto.getVolume());
            existingLiquid.setPrice(liquidRequestDto.getPrice());
            existingLiquid.setName(liquidRequestDto.getName());
            existingLiquid.setIdentifier(liquidRequestDto.getIdentifier());
            existingLiquid.setDescription(liquidRequestDto.getDescription());

            Liquid updatedLiquid = liquidRepository.save(existingLiquid);
            return liquidMapper.toDto(updatedLiquid);
        } else {
            throw new EntityNotFoundException("Liquid with ID " + id + " not found");
        }
    }
    }
}
