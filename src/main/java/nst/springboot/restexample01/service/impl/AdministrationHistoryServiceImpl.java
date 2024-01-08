package nst.springboot.restexample01.service.impl;

import nst.springboot.restexample01.converter.impl.*;
import nst.springboot.restexample01.domain.*;
import nst.springboot.restexample01.dto.AdministrationHistoryDto;
import nst.springboot.restexample01.repository.*;
import nst.springboot.restexample01.service.AdministrationHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdministrationHistoryServiceImpl implements AdministrationHistoryService {

    private DepartmentConverter departmentConverter;
    private DepartmentRepository departmentRepository;
    private MemberConverter memberConverter;
    private MemberRepository memberRepository;
    private AdministrationHistoryConverter administrationHistoryConverter;
    private AdministrationHistoryRepository administrationHistoryRepository;

    public AdministrationHistoryServiceImpl(
            DepartmentRepository departmentRepository,
            DepartmentConverter departmentConverter,
            MemberRepository memberRepository,
            MemberConverter memberConverter,
            AdministrationHistoryConverter administrationHistoryConverter,
            AdministrationHistoryRepository administrationHistoryRepository
    ) {
        this.departmentConverter = departmentConverter;
        this.departmentRepository = departmentRepository;
        this.memberConverter = memberConverter;
        this.memberRepository = memberRepository;
        this.administrationHistoryConverter = administrationHistoryConverter;
        this.administrationHistoryRepository = administrationHistoryRepository;
    }

    @Override
    @Transactional
    public AdministrationHistoryDto save(AdministrationHistoryDto administrationHistoryDto) throws Exception {
        AdministrationHistory administrationHistory = administrationHistoryConverter.toEntity(administrationHistoryDto);

        administrationHistory.getDepartment().setId(handleDepartment(administrationHistory));
        administrationHistory.getHeadOfDepartment().setId(handleHeadOfDepartment(administrationHistory));
        administrationHistory.getSecretary().setId(handleSecretary(administrationHistory));

        AdministrationHistory administrationHistory1 = administrationHistoryRepository.save(administrationHistory);
        return administrationHistoryConverter.toDto(administrationHistory1);
    }

    private Long handleDepartment(AdministrationHistory administrationHistory) {
        if (administrationHistory.getDepartment().getId() == null) {
            Optional<Department> departmentOptional = departmentRepository.findByName(administrationHistory.getDepartment().getName());
            if (departmentOptional.isEmpty()) {
                Department department = departmentRepository.save(administrationHistory.getDepartment());
                return department.getId();
            }
            return departmentOptional.get().getId();
        } else {
            Optional<Department> departmentOptional = departmentRepository.findById(administrationHistory.getDepartment().getId());
            if (departmentOptional.isEmpty()) {
                Department department = departmentRepository.save(administrationHistory.getDepartment());
                return department.getId();
            }
            return departmentOptional.get().getId();
        }
    }

    private Long handleHeadOfDepartment(AdministrationHistory administrationHistory) {
        if (administrationHistory.getHeadOfDepartment().getId() == null) {
            Member headOfDepartment = memberRepository.save(administrationHistory.getHeadOfDepartment());
            return headOfDepartment.getId();
        } else {
            Optional<Member> memberOptional = memberRepository.findById(administrationHistory.getHeadOfDepartment().getId());
            if (memberOptional.isEmpty()) {
                Member headOfDepartment = memberRepository.save(administrationHistory.getHeadOfDepartment());
                return headOfDepartment.getId();
            }
            return memberOptional.get().getId();
        }
    }

    private Long handleSecretary(AdministrationHistory administrationHistory) {
        if (administrationHistory.getSecretary().getId() == null) {
            Member secretary = memberRepository.save(administrationHistory.getSecretary());
            return secretary.getId();
        } else {
            Optional<Member> memberOptional = memberRepository.findById(administrationHistory.getSecretary().getId());
            if (memberOptional.isEmpty()) {
                Member secretary = memberRepository.save(administrationHistory.getSecretary());
                return secretary.getId();
            }
            return memberOptional.get().getId();
        }
    }


    @Override
    public List<AdministrationHistoryDto> getAll() {
        return administrationHistoryRepository
                .findAll()
                .stream().map(entity -> administrationHistoryConverter.toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<AdministrationHistory> administrationHistoryOptional = administrationHistoryRepository.findById(id);
        if (administrationHistoryOptional.isPresent()) {
            AdministrationHistory administrationHistory = administrationHistoryOptional.get();
            administrationHistoryRepository.delete(administrationHistory);
        } else {
            throw new Exception("Administration history does not exist!");
        }

    }

    @Override
    public void update(AdministrationHistoryDto administrationHistoryDto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AdministrationHistoryDto findById(Long id) throws Exception {
        Optional<AdministrationHistory> administrationHistoryOptional = administrationHistoryRepository.findById(id);
        if (administrationHistoryOptional.isPresent()) {
            AdministrationHistory administrationHistory = administrationHistoryOptional.get();
            return administrationHistoryConverter.toDto(administrationHistory);
        } else {
            throw new Exception("Administration history does not exist!");
        }
    }
}
