package nst.springboot.restexample01.service;

import nst.springboot.restexample01.dto.AdministrationHistoryDto;

import java.util.List;

public interface AdministrationHistoryService {
    AdministrationHistoryDto save(AdministrationHistoryDto administrationHistoryDto) throws Exception;

    List<AdministrationHistoryDto> getAll();

    void delete(Long id) throws Exception;

    void update(AdministrationHistoryDto administrationHistoryDto) throws Exception;

    AdministrationHistoryDto findById(Long id) throws Exception;
}
