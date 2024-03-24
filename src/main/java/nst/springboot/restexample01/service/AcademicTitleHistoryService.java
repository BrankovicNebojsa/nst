package nst.springboot.restexample01.service;

import nst.springboot.restexample01.dto.AcademicTitleHistoryDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AcademicTitleHistoryService {
    AcademicTitleHistoryDto save(AcademicTitleHistoryDto academicTitleHistoryDto) throws Exception;

    List<AcademicTitleHistoryDto> getAll();

    List<AcademicTitleHistoryDto> getAll(Pageable pageable);

    void delete(Long id) throws Exception;

    AcademicTitleHistoryDto update(Long id, AcademicTitleHistoryDto academicTitleHistoryDto) throws Exception;

    AcademicTitleHistoryDto findById(Long id) throws Exception;
}
