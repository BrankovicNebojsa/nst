package nst.springboot.restexample01.service;

import nst.springboot.restexample01.dto.AdministrationHistoryDto;
import nst.springboot.restexample01.dto.MemberDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdministrationHistoryService {
    AdministrationHistoryDto save(AdministrationHistoryDto administrationHistoryDto) throws Exception;

    List<AdministrationHistoryDto> getAll();

    List<AdministrationHistoryDto> getAll(Pageable pageable);

    void delete(Long id) throws Exception;

    void update(AdministrationHistoryDto administrationHistoryDto) throws Exception;

    AdministrationHistoryDto findById(Long id) throws Exception;

    MemberDto findCurrentHeadOfDepartmentByDepartmentId(Long id) throws Exception;

    MemberDto findCurrentSecretaryByDepartmentId(Long id) throws Exception;

}
