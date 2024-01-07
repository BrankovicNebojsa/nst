/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.service.impl;

import nst.springboot.restexample01.converter.impl.*;
import nst.springboot.restexample01.domain.*;
import nst.springboot.restexample01.dto.MemberDto;
import nst.springboot.restexample01.repository.*;
import nst.springboot.restexample01.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    private DepartmentConverter departmentConverter;
    private DepartmentRepository departmentRepository;
    private AcademicTitleConverter academicTitleConverter;
    private AcademicTitleRepository academicTitleRepository;
    private ScientificFieldConverter scientificFieldConverter;
    private ScientificFieldRepository scientificFieldRepository;
    private EducationTitleConverter educationTitleConverter;
    private EducationTitleRepository educationTitleRepository;
    private MemberConverter memberConverter;
    private MemberRepository memberRepository;

    public MemberServiceImpl(
            MemberRepository memberRepository,
            MemberConverter memberConverter,
            AcademicTitleConverter academicTitleConverter,
            AcademicTitleRepository academicTitleRepository,
            ScientificFieldConverter scientificFieldConverter,
            ScientificFieldRepository scientificFieldRepository,
            EducationTitleConverter educationTitleConverter,
            EducationTitleRepository educationTitleRepository,
            DepartmentRepository departmentRepository,
            DepartmentConverter departmentConverter) {
        this.departmentConverter = departmentConverter;
        this.departmentRepository = departmentRepository;
        this.academicTitleConverter = academicTitleConverter;
        this.academicTitleRepository = academicTitleRepository;
        this.scientificFieldConverter = scientificFieldConverter;
        this.scientificFieldRepository = scientificFieldRepository;
        this.educationTitleConverter = educationTitleConverter;
        this.educationTitleRepository = educationTitleRepository;
        this.memberConverter = memberConverter;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public MemberDto save(MemberDto memberDto) throws Exception {
        Member member = memberConverter.toEntity(memberDto);

        member.getDepartment().setId(handleDepartment(member));
        member.getAcademicTitle().setId(handleAcademicTitle(member));
        member.getScientificField().setId(handleScientificField(member));
        member.getEducationTitle().setId(handleEducationTitle(member));

        memberRepository.save(member);
        return memberDto;
    }

    private Long handleDepartment(Member member) {
        if (member.getDepartment().getId() == null) {
            Optional<Department> departmentOptional = departmentRepository.findByName(member.getDepartment().getName());
            if (departmentOptional.isEmpty()) {
                Department department = departmentRepository.save(member.getDepartment());
                return department.getId();
            }
            return departmentOptional.get().getId();
        } else {
            Optional<Department> departmentOptional = departmentRepository.findById(member.getDepartment().getId());
            if (departmentOptional.isEmpty()) {
                Department department = departmentRepository.save(member.getDepartment());
                return department.getId();
            }
            return departmentOptional.get().getId();
        }
    }

    private Long handleAcademicTitle(Member member) {
        if (member.getAcademicTitle().getId() == null) {
            Optional<AcademicTitle> academicTitleOptional = academicTitleRepository.findByName(member.getAcademicTitle().getName());
            if (academicTitleOptional.isEmpty()) {
                AcademicTitle academicTitle = academicTitleRepository.save(member.getAcademicTitle());
                return academicTitle.getId();
            }
            return academicTitleOptional.get().getId();
        } else {
            Optional<AcademicTitle> academicTitleOptional = academicTitleRepository.findById(member.getAcademicTitle().getId());
            if (academicTitleOptional.isEmpty()) {
                AcademicTitle academicTitle = academicTitleRepository.save(member.getAcademicTitle());
                return academicTitle.getId();
            }
            return academicTitleOptional.get().getId();
        }
    }

    private Long handleScientificField(Member member) {
        if (member.getScientificField().getId() == null) {
            Optional<ScientificField> scientificFieldOptional = scientificFieldRepository.findByName(member.getScientificField().getName());
            if (scientificFieldOptional.isEmpty()) {
                ScientificField scientificField = scientificFieldRepository.save(member.getScientificField());
                return scientificField.getId();
            }
            return scientificFieldOptional.get().getId();
        } else {
            Optional<ScientificField> scientificFieldOptional = scientificFieldRepository.findById(member.getScientificField().getId());
            if (scientificFieldOptional.isEmpty()) {
                ScientificField scientificField = scientificFieldRepository.save(member.getScientificField());
                return scientificField.getId();
            }
            return scientificFieldOptional.get().getId();
        }
    }

    private Long handleEducationTitle(Member member) {
        if (member.getEducationTitle().getId() == null) {
            Optional<EducationTitle> educationTitleOptional = educationTitleRepository.findByName(member.getEducationTitle().getName());
            if (educationTitleOptional.isEmpty()) {
                EducationTitle eduTitle = educationTitleRepository.save(member.getEducationTitle());
                return eduTitle.getId();
            }
            return educationTitleOptional.get().getId();
        } else {
            Optional<EducationTitle> educationTitle = educationTitleRepository.findById(member.getEducationTitle().getId());
            if (educationTitle.isEmpty()) {
                EducationTitle eduTitle = educationTitleRepository.save(member.getEducationTitle());
                return eduTitle.getId();
            }
            return educationTitle.get().getId();
        }
    }


    @Override
    public List<MemberDto> getAll() {
        return memberRepository
                .findAll()
                .stream().map(entity -> memberConverter.toDto(entity))
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
