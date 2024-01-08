package nst.springboot.restexample01.service.impl;

import nst.springboot.restexample01.converter.impl.*;
import nst.springboot.restexample01.domain.*;
import nst.springboot.restexample01.dto.AdministrationHistoryDto;
import nst.springboot.restexample01.repository.*;
import nst.springboot.restexample01.service.AdministrationHistoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdministrationHistoryServiceImpl implements AdministrationHistoryService {
    private final DepartmentRepository departmentRepository;
    private final MemberRepository memberRepository;
    private final AdministrationHistoryConverter administrationHistoryConverter;
    private final AdministrationHistoryRepository administrationHistoryRepository;

    public AdministrationHistoryServiceImpl(
            DepartmentRepository departmentRepository,
            MemberRepository memberRepository,
            AdministrationHistoryConverter administrationHistoryConverter,
            AdministrationHistoryRepository administrationHistoryRepository
    ) {
        this.departmentRepository = departmentRepository;
        this.memberRepository = memberRepository;
        this.administrationHistoryConverter = administrationHistoryConverter;
        this.administrationHistoryRepository = administrationHistoryRepository;
    }

    @Override
    @Transactional
    public AdministrationHistoryDto save(AdministrationHistoryDto administrationHistoryDto) throws Exception {
        AdministrationHistory administrationHistory = administrationHistoryConverter.toEntity(administrationHistoryDto);

        administrationHistory.setDepartment(handleDepartment(administrationHistory));
        administrationHistory.setHeadOfDepartment(handleHeadOfDepartment(administrationHistory));
        administrationHistory.setSecretary(handleSecretary(administrationHistory));

        AdministrationHistory administrationHistory1 = administrationHistoryRepository.save(administrationHistory);
        return administrationHistoryConverter.toDto(administrationHistory1);
    }

    private Department handleDepartment(AdministrationHistory administrationHistory) {
        if (administrationHistory.getDepartment().getId() == null) {
            Optional<Department> departmentOptional = departmentRepository.findByName(administrationHistory.getDepartment().getName());
            return departmentOptional.orElseGet(() -> departmentRepository.save(administrationHistory.getDepartment()));
        } else {
            Optional<Department> departmentOptional = departmentRepository.findById(administrationHistory.getDepartment().getId());
            return departmentOptional.orElseGet(() -> departmentRepository.save(administrationHistory.getDepartment()));
        }
    }

    private Member handleHeadOfDepartment(AdministrationHistory administrationHistory) {
        if (administrationHistory.getHeadOfDepartment().getId() == null) {
            return memberRepository.save(administrationHistory.getHeadOfDepartment());
        } else {
            Optional<Member> memberOptional = memberRepository.findById(administrationHistory.getHeadOfDepartment().getId());
            return memberOptional.orElseGet(() -> memberRepository.save(administrationHistory.getHeadOfDepartment()));
        }
    }

    private Member handleSecretary(AdministrationHistory administrationHistory) {
        if (administrationHistory.getSecretary().getId() == null) {
            return memberRepository.save(administrationHistory.getSecretary());
        } else {
            Optional<Member> memberOptional = memberRepository.findById(administrationHistory.getSecretary().getId());
            return memberOptional.orElseGet(() -> memberRepository.save(administrationHistory.getSecretary()));
        }
    }


    @Override
    public List<AdministrationHistoryDto> getAll() {
        return administrationHistoryRepository
                .findAll()
                .stream().map(administrationHistoryConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AdministrationHistoryDto> getAll(Pageable pageable) {
        return administrationHistoryRepository
                .findAll(pageable).getContent()
                .stream().map(administrationHistoryConverter::toDto)
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
