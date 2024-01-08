package nst.springboot.restexample01.service;

import nst.springboot.restexample01.dto.SubjectDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubjectService {
    SubjectDto save(SubjectDto subjectDto) throws Exception;

    List<SubjectDto> getAll();

    List<SubjectDto> getAll(Pageable pageable);

    void delete(Long id) throws Exception;

    void update(SubjectDto subjectDto) throws Exception;

    SubjectDto findById(Long id) throws Exception;
}
