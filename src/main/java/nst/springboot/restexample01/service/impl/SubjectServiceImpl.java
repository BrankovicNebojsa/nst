package nst.springboot.restexample01.service.impl;

import nst.springboot.restexample01.converter.impl.SubjectConverter;
import nst.springboot.restexample01.domain.Department;
import nst.springboot.restexample01.domain.ScientificField;
import nst.springboot.restexample01.domain.Subject;
import nst.springboot.restexample01.dto.SubjectDto;
import nst.springboot.restexample01.exception.EntityAlreadyExistsException;
import nst.springboot.restexample01.repository.DepartmentRepository;
import nst.springboot.restexample01.repository.SubjectRepository;
import nst.springboot.restexample01.service.SubjectService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final DepartmentRepository departmentRepository;
    private final SubjectConverter subjectConverter;
    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(DepartmentRepository departmentRepository, SubjectConverter subjectConverter, SubjectRepository subjectRepository) {
        this.departmentRepository = departmentRepository;
        this.subjectConverter = subjectConverter;
        this.subjectRepository = subjectRepository;
    }

    @Override
    @Transactional
    public SubjectDto save(SubjectDto subjectDto) throws Exception {

        Optional<Subject> subjectOptional = subjectRepository.findByName(subjectDto.getName());
        if (subjectOptional.isPresent()) {
            throw new EntityAlreadyExistsException("Subject with that name already exists!");
        } else {
            Subject subject = subjectConverter.toEntity(subjectDto);
            subject = subjectRepository.save(subject);
            return subjectConverter.toDto(subject);
        }
    }

    private Department handleDepartment(Subject subject) {
        if (subject.getDepartment().getId() == null) {
            return departmentRepository.save(subject.getDepartment());
        } else {
            Optional<Department> departmentOptional = departmentRepository.findById(subject.getDepartment().getId());
            return departmentOptional.orElseGet(() -> departmentRepository.save(subject.getDepartment()));
        }
    }

    @Override
    public List<SubjectDto> getAll() {
        return subjectRepository.findAll().stream().map(subjectConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public List<SubjectDto> getAll(Pageable pageable) {
        return subjectRepository.findAll(pageable).getContent().stream().map(subjectConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            Subject subj = subject.get();
            subjectRepository.delete(subj);
        } else {
            throw new Exception("Subject does not exist!");
        }

    }

    @Transactional
    @Override
    public SubjectDto update(Long id, SubjectDto subjectDto) throws Exception {
        Optional<Subject> subjectOptional = subjectRepository.findById(id);
        if (subjectOptional.isPresent()) {
            subjectDto.setId(subjectOptional.get().getId());
            return subjectConverter.toDto(subjectRepository.save(subjectConverter.toEntity(subjectDto)));
        }
        throw new Exception("Subject with that id does not exist");
    }

    @Override
    public SubjectDto findById(Long id) throws Exception {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            Subject subj = subject.get();
            return subjectConverter.toDto(subj);
        } else {
            throw new Exception("Subject does not exist!");
        }
    }

}
