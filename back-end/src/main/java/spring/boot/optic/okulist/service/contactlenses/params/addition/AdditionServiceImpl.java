package spring.boot.optic.okulist.service.contactlenses.params.addition;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.contactlenses.parameters.addition.AdditionRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.parameters.addition.AdditionResponseDto;
import spring.boot.optic.okulist.mapper.contactlenses.AdditionMapper;
import spring.boot.optic.okulist.model.lenses.parameters.Addition;
import spring.boot.optic.okulist.repository.lenses.paramsrepository.AdditionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdditionServiceImpl implements AdditionService {
    private final AdditionRepository additionRepository;
    private final AdditionMapper additionMapper;

    @Override
    public AdditionResponseDto createAddition(AdditionRequestDto additionRequestDto) {
        Addition addition = additionMapper.toModel(additionRequestDto);
        return additionMapper.toDto(additionRepository.save(addition));
    }

    @Override
    public AdditionResponseDto getAdditionById(Long id) {
        Addition addition = additionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Addition not found with id: " + id));
        return additionMapper.toDto(addition);
    }

    @Override
    public List<AdditionResponseDto> getAllAdditions() {
        List<Addition> additions = additionRepository.findAll();
        return additionMapper.toDtoList(additions);
    }
}
