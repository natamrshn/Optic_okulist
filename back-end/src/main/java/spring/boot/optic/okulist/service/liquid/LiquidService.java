package spring.boot.optic.okulist.service.liquid;

import java.util.List;
import org.springframework.data.domain.Pageable;
import spring.boot.optic.okulist.dto.liquid.LiquidRequestDto;
import spring.boot.optic.okulist.dto.liquid.LiquidResponseDto;

public interface LiquidService {
    List<LiquidResponseDto> findAll(Pageable pageable);

    LiquidResponseDto getById(Long id);

    LiquidResponseDto save(LiquidRequestDto liquidRequestDto);

    void deleteById(Long id);

    public List<LiquidResponseDto> findSimilar(LiquidRequestDto liquidRequestDto);

    public LiquidResponseDto update(Long id, LiquidRequestDto liquidRequestDto);
}
