package nst.springboot.restexample01;

import nst.springboot.restexample01.dto.*;
import nst.springboot.restexample01.service.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemberServiceTest extends AbstractTest {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private AcademicTitleService academicTitleService;
    @Autowired
    private ScientificFieldService scientificFieldService;
    @Autowired
    private EducationTitleService educationTitleService;
    @Autowired
    private MemberService memberService;

    private DepartmentDto departmentDto;
    private AcademicTitleDto academicTitleDto;
    private ScientificFieldDto scientificFieldDto;
    private EducationTitleDto educationTitleDto;
    private MemberDto memberDto;

    @BeforeEach
    public void setUp() throws Exception {
        departmentDto = departmentService.save(ApplicationTestData.department);
        academicTitleDto = academicTitleService.save(ApplicationTestData.academicTitle);
        scientificFieldDto = scientificFieldService.save(ApplicationTestData.scientificField);
        educationTitleDto = educationTitleService.save(ApplicationTestData.educationTitle);
        memberDto = ApplicationTestData.member;
        memberDto.setDepartmentDto(departmentDto);
        memberDto.setAcademicTitleDto(academicTitleDto);
        memberDto.setScientificFieldDto(scientificFieldDto);
        memberDto.setEducationTitleDto(educationTitleDto);
    }

    @AfterEach
    public void tearDown() {
        List<MemberDto> members = memberService.getAll();
        members.forEach(member -> {
            try {
                memberService.delete(member.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        List<DepartmentDto> departments = departmentService.getAll();
        departments.forEach(department -> {
            try {
                departmentService.delete(department.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        List<AcademicTitleDto> academicTitles = academicTitleService.getAll();
        academicTitles.forEach(academicTitleDto -> {
            try {
                academicTitleService.delete(academicTitleDto.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        List<ScientificFieldDto> scientificFields = scientificFieldService.getAll();
        scientificFields.forEach(scientificFieldDto -> {
            try {
                scientificFieldService.delete(scientificFieldDto.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        List<EducationTitleDto> educationTitles = educationTitleService.getAll();
        educationTitles.forEach(educationTitleDto -> {
            try {
                educationTitleService.delete(educationTitleDto.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void save() throws Exception {
        int initialSize = memberService.getAll().size();
        memberService.save(memberDto);
        List<MemberDto> members = memberService.getAll();

        assertEquals(initialSize + 1, members.size());
    }

    @Test
    public void findById() throws Exception {
        memberService.save(memberDto);
        List<MemberDto> members = memberService.getAll();
        MemberDto memberDto1 = members.stream().filter(memberDto -> memberDto.equals(this.memberDto)).findFirst().get();

        MemberDto member = memberService.findById(memberDto1.getId());
        assertEquals(memberDto, member);
    }

    @Test
    public void update() throws Exception {
        memberService.save(memberDto);
        List<MemberDto> members = memberService.getAll();
        MemberDto memberDto1 = members.stream().filter(member -> member.equals(memberDto)).findFirst().get();

        memberDto.setFirstName("New First Name");
        memberDto.setLastName("New Last Name");
        memberService.update(memberDto1.getId(), memberDto);

        members = memberService.getAll();
        memberDto1 = members.stream().filter(member -> member.equals(memberDto)).findFirst().get();

        assertEquals("New First Name", memberDto1.getFirstName());
        assertEquals("New Last Name", memberDto1.getLastName());
    }

    @Test
    public void delete() throws Exception {
        memberService.save(memberDto);
        List<MemberDto> members = memberService.getAll();
        MemberDto memberDto1 = members.stream().filter(member -> member.equals(memberDto)).findFirst().get();

        memberService.delete(memberDto1.getId());
        assertThrows(Exception.class, () -> memberService.findById(memberDto1.getId()));
    }
}
