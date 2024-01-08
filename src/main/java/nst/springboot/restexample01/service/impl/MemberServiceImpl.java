package nst.springboot.restexample01.service.impl;

import nst.springboot.restexample01.converter.impl.MemberConverter;
import nst.springboot.restexample01.domain.*;
import nst.springboot.restexample01.dto.MemberDto;
import nst.springboot.restexample01.repository.*;
import nst.springboot.restexample01.service.MemberService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {
    private final DepartmentRepository departmentRepository;
    private final AcademicTitleRepository academicTitleRepository;
    private final ScientificFieldRepository scientificFieldRepository;
    private final EducationTitleRepository educationTitleRepository;
    private final MemberConverter memberConverter;
    private final MemberRepository memberRepository;

    public MemberServiceImpl(
            DepartmentRepository departmentRepository,
            AcademicTitleRepository academicTitleRepository,
            ScientificFieldRepository scientificFieldRepository,
            EducationTitleRepository educationTitleRepository,
            MemberConverter memberConverter,
            MemberRepository memberRepository) {
        this.departmentRepository = departmentRepository;
        this.academicTitleRepository = academicTitleRepository;
        this.scientificFieldRepository = scientificFieldRepository;
        this.educationTitleRepository = educationTitleRepository;
        this.memberConverter = memberConverter;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public MemberDto save(MemberDto memberDto) throws Exception {
        Member member = memberConverter.toEntity(memberDto);

        member.setDepartment(handleDepartment(member));
        member.setAcademicTitle(handleAcademicTitle(member));
        member.setScientificField(handleScientificField(member));
        member.setEducationTitle(handleEducationTitle(member));

        Member member1 = memberRepository.save(member);
        return memberConverter.toDto(member1);
    }

    private Department handleDepartment(Member member) {
        if (member.getDepartment().getId() == null) {
            Optional<Department> departmentOptional = departmentRepository.findByName(member.getDepartment().getName());
            return departmentOptional.orElseGet(() -> departmentRepository.save(member.getDepartment()));
        } else {
            Optional<Department> departmentOptional = departmentRepository.findById(member.getDepartment().getId());
            return departmentOptional.orElseGet(() -> departmentRepository.save(member.getDepartment()));
        }
    }

    private AcademicTitle handleAcademicTitle(Member member) {
        if (member.getAcademicTitle().getId() == null) {
            Optional<AcademicTitle> academicTitleOptional = academicTitleRepository.findByName(member.getAcademicTitle().getName());
            return academicTitleOptional.orElseGet(() -> academicTitleRepository.save(member.getAcademicTitle()));
        } else {
            Optional<AcademicTitle> academicTitleOptional = academicTitleRepository.findById(member.getAcademicTitle().getId());
            return academicTitleOptional.orElseGet(() -> academicTitleRepository.save(member.getAcademicTitle()));
        }
    }

    private ScientificField handleScientificField(Member member) {
        if (member.getScientificField().getId() == null) {
            Optional<ScientificField> scientificFieldOptional = scientificFieldRepository.findByName(member.getScientificField().getName());
            return scientificFieldOptional.orElseGet(() -> scientificFieldRepository.save(member.getScientificField()));
        } else {
            Optional<ScientificField> scientificFieldOptional = scientificFieldRepository.findById(member.getScientificField().getId());
            return scientificFieldOptional.orElseGet(() -> scientificFieldRepository.save(member.getScientificField()));
        }
    }

    private EducationTitle handleEducationTitle(Member member) {
        if (member.getEducationTitle().getId() == null) {
            Optional<EducationTitle> educationTitleOptional = educationTitleRepository.findByName(member.getEducationTitle().getName());
            return educationTitleOptional.orElseGet(() -> educationTitleRepository.save(member.getEducationTitle()));
        } else {
            Optional<EducationTitle> educationTitle = educationTitleRepository.findById(member.getEducationTitle().getId());
            return educationTitle.orElseGet(() -> educationTitleRepository.save(member.getEducationTitle()));
        }
    }


    @Override
    public List<MemberDto> getAll() {
        return memberRepository
                .findAll()
                .stream().map(memberConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberDto> getAll(Pageable pageable) {
        return memberRepository
                .findAll(pageable).getContent()
                .stream().map(memberConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws Exception {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            Member member1 = member.get();
            memberRepository.delete(member1);
        } else {
            throw new Exception("Member does not exist!");
        }

    }

    @Override
    public void update(MemberDto memberDto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public MemberDto findById(Long id) throws Exception {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            Member member1 = member.get();
            return memberConverter.toDto(member1);
        } else {
            throw new Exception("Member does not exist!");
        }
    }

}
