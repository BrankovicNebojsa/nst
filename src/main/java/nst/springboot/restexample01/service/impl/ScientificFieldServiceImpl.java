package nst.springboot.restexample01.service.impl;

import nst.springboot.restexample01.converter.impl.ScientificFieldConverter;
import nst.springboot.restexample01.domain.ScientificField;
import nst.springboot.restexample01.dto.ScientificFieldDto;
import nst.springboot.restexample01.exception.EntityAlreadyExistsException;
import nst.springboot.restexample01.repository.ScientificFieldRepository;
import nst.springboot.restexample01.service.ScientificFieldService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScientificFieldServiceImpl implements ScientificFieldService {
    private ScientificFieldRepository scientificFieldRepository;
    private ScientificFieldConverter scientificFieldConverter;

    public ScientificFieldServiceImpl(ScientificFieldRepository scientificFieldRepository, ScientificFieldConverter scientificFieldConverter) {
        this.scientificFieldRepository = scientificFieldRepository;
        this.scientificFieldConverter = scientificFieldConverter;
    }

    @Override
    public ScientificFieldDto save(ScientificFieldDto scientificFieldDto) throws Exception {
        Optional<ScientificField> scientificFieldOptional = scientificFieldRepository.findByName(scientificFieldDto.getName());
        if (scientificFieldOptional.isPresent()) {
            throw new EntityAlreadyExistsException("Scientific field with that name already exists!");
        } else {
            ScientificField scientificField = scientificFieldConverter.toEntity(scientificFieldDto);
            scientificField = scientificFieldRepository.save(scientificField);
            return scientificFieldConverter.toDto(scientificField);
        }
    }

    @Override
    public List<ScientificFieldDto> getAll() {
        return scientificFieldRepository
                .findAll()
                .stream().map(entity -> scientificFieldConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScientificFieldDto> getAll(Pageable pageable) {
        return scientificFieldRepository
                .findAll(pageable).getContent()
                .stream().map(entity -> scientificFieldConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<ScientificField> scienceField = scientificFieldRepository.findById(id);
        if (scienceField.isPresent()) {
            ScientificField scientificField = scienceField.get();
            scientificFieldRepository.delete(scientificField);
        } else {
            throw new Exception("Scientific field does not exist!");
        }
    }

    @Transactional
    @Override
    public ScientificFieldDto update(Long id, ScientificFieldDto scientificFieldDto) throws Exception {
        Optional<ScientificField> scientificFieldOptional = scientificFieldRepository.findById(id);
        if (scientificFieldOptional.isPresent()) {
            scientificFieldDto.setId(scientificFieldOptional.get().getId());
            return scientificFieldConverter.toDto(scientificFieldRepository.save(scientificFieldConverter.toEntity(scientificFieldDto)));
        }
        throw new Exception("Scientific field with that id does not exist");
    }

    @Override
    public ScientificFieldDto findById(Long id) throws Exception {
        Optional<ScientificField> scienceField = scientificFieldRepository.findById(id);
        if (scienceField.isPresent()) {
            ScientificField scientificField = scienceField.get();
            return scientificFieldConverter.toDto(scientificField);
        } else {
            throw new Exception("Scientific field does not exist!");
        }
    }
}
