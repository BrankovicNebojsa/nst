package nst.springboot.restexample01.controller.service.impl;

import nst.springboot.restexample01.controller.domain.Department;
import nst.springboot.restexample01.controller.domain.EducationTitle;
import nst.springboot.restexample01.controller.repository.EducationTitleRepository;
import nst.springboot.restexample01.controller.service.EducationTitleService;
import nst.springboot.restexample01.converter.impl.EducationTitleConverter;
import nst.springboot.restexample01.dto.EducationTitleDto;
import nst.springboot.restexample01.exception.EducationalTitleAlreadyExistsException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EducationTitleServiceImpl implements EducationTitleService {
    private EducationTitleRepository educationTitleRepository;
    private EducationTitleConverter educationTitleConverter;

    public EducationTitleServiceImpl(EducationTitleRepository educationTitleRepository, EducationTitleConverter educationTitleConverter) {
        this.educationTitleRepository = educationTitleRepository;
        this.educationTitleConverter = educationTitleConverter;
    }

    @Override
    public EducationTitleDto save(EducationTitleDto educationTitleDto) throws Exception {
        Optional<EducationTitle> eduTitleOptional = educationTitleRepository.findByName(educationTitleDto.getName());
        if (eduTitleOptional.isPresent()) {
            throw new EducationalTitleAlreadyExistsException("Zvanje sa tim nazivom vec postoji!");
        } else {
            //DTO - > Entity
            //Department department = new Department(departmentDto.getId(), departmentDto.getName());
            EducationTitle educationTitle = educationTitleConverter.toEntity(educationTitleDto);
            educationTitle = educationTitleRepository.save(educationTitle);
            return educationTitleConverter.toDto(educationTitle);
        }
    }

    @Override
    public List<EducationTitleDto> getAll() {
        return educationTitleRepository
                .findAll()
                .stream().map(entity -> educationTitleConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<EducationTitleDto> getAll(Pageable pageable) {
        return educationTitleRepository
                .findAll(pageable).getContent()
                .stream().map(entity -> educationTitleConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        //proveri da li postoji education title
        Optional<EducationTitle> eduTitle = educationTitleRepository.findById(id);
        if (eduTitle.isPresent()) {
            //postoji
            EducationTitle educationTitle = eduTitle.get();
            educationTitleRepository.delete(educationTitle);
        } else {
            throw new Exception("Education title does not exist!");
        }
    }

    @Override
    public void update(EducationTitleDto educationTitleDto) throws Exception {

    }

    @Override
    public EducationTitleDto findById(Long id) throws Exception {
        Optional<EducationTitle> eduTitle = educationTitleRepository.findById(id);
        if (eduTitle.isPresent()) {
            //postoji
            EducationTitle educationTitle = eduTitle.get();
            return educationTitleConverter.toDto(educationTitle);
        } else {
            throw new Exception("Education title does not exist!");
        }
    }
}
