package nst.springboot.restexample01.service;

import nst.springboot.restexample01.dto.ScientificFieldDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScientificFieldService {
    ScientificFieldDto save(ScientificFieldDto scientificFieldDto) throws Exception;

    List<ScientificFieldDto> getAll();

    List<ScientificFieldDto> getAll(Pageable pageable);

    void delete(Long id) throws Exception;

    void update(ScientificFieldDto scientificFieldDto) throws Exception;

    ScientificFieldDto findById(Long id) throws Exception;
}
