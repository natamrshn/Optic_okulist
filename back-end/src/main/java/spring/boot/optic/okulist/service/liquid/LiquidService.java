package spring.boot.optic.okulist.service.liquid;

import java.util.List;
import spring.boot.optic.okulist.dto.liquid.LiquidRequestDto;
import spring.boot.optic.okulist.dto.liquid.LiquidResponseDto;
import spring.boot.optic.okulist.dto.liquid.LiquidSearchParameter;

public interface LiquidService {
    List<LiquidResponseDto> findAll();

    LiquidResponseDto getById(Long id);

    LiquidResponseDto save(LiquidRequestDto liquidRequestDto);

    void deleteById(Long id);

    public LiquidResponseDto update(Long id, LiquidRequestDto liquidRequestDto);

    List<LiquidResponseDto> searchLiquidByParameters(LiquidSearchParameter searchParameters);

    List<LiquidResponseDto> findSimilar(LiquidSearchParameter liquidRequestDto);

    LiquidResponseDto findByIdentifier(String identifier);
}
