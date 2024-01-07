/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.dto;

import java.io.Serializable;

public class MemberDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private DepartmentDto departmentDto;
    private AcademicTitleDto academicTitleDto;
    private ScientificFieldDto scientificFieldDto;
    private EducationTitleDto educationTitleDto;

    public MemberDto() {
    }

    public MemberDto(String firstName,
                     String lastName,
                     DepartmentDto departmentDto,
                     AcademicTitleDto academicTitleDto,
                     ScientificFieldDto scientificFieldDto,
                     EducationTitleDto educationTitleDto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentDto = departmentDto;
        this.academicTitleDto = academicTitleDto;
        this.scientificFieldDto = scientificFieldDto;
        this.educationTitleDto = educationTitleDto;
    }

    public MemberDto(Long id,
                     String firstName,
                     String lastName,
                     DepartmentDto departmentDto,
                     AcademicTitleDto academicTitleDto,
                     ScientificFieldDto scientificFieldDto,
                     EducationTitleDto educationTitleDto) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentDto = departmentDto;
        this.academicTitleDto = academicTitleDto;
        this.scientificFieldDto = scientificFieldDto;
        this.educationTitleDto = educationTitleDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DepartmentDto getDepartmentDto() {
        return departmentDto;
    }

    public void setDepartmentDto(DepartmentDto departmentDto) {
        this.departmentDto = departmentDto;
    }

    public AcademicTitleDto getAcademicTitleDto() {
        return academicTitleDto;
    }

    public void setAcademicTitleDto(AcademicTitleDto academicTitleDto) {
        this.academicTitleDto = academicTitleDto;
    }

    public ScientificFieldDto getScientificFieldDto() {
        return scientificFieldDto;
    }

    public void setScientificFieldDto(ScientificFieldDto scientificFieldDto) {
        this.scientificFieldDto = scientificFieldDto;
    }

    public EducationTitleDto getEducationTitleDto() {
        return educationTitleDto;
    }

    public void setEducationTitleDto(EducationTitleDto educationTitleDto) {
        this.educationTitleDto = educationTitleDto;
    }
}
