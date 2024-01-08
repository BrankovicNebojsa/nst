package nst.springboot.restexample01.service;

import nst.springboot.restexample01.dto.MemberDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {
    MemberDto save(MemberDto memberDto) throws Exception;

    List<MemberDto> getAll();

    List<MemberDto> getAll(Pageable pageable);

    List<MemberDto> getAllByDepartmentId(Long id);

    void delete(Long id) throws Exception;

    void update(MemberDto memberDto) throws Exception;

    MemberDto findById(Long id) throws Exception;
}
