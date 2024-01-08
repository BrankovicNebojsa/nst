package nst.springboot.restexample01.dto;

import java.io.Serializable;
import java.util.Date;

public class AdministrationHistoryDto implements Serializable {
    private Long id;
    private Date startDate;
    private Date endDate;
    private DepartmentDto departmentDto;
    private MemberDto headOfDepartmentDto;
    private MemberDto secretaryDto;

    public AdministrationHistoryDto() {
    }

    public AdministrationHistoryDto(Date startDate, Date endDate, DepartmentDto departmentDto, MemberDto headOfDepartmentDto, MemberDto secretaryDto) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.departmentDto = departmentDto;
        this.headOfDepartmentDto = headOfDepartmentDto;
        this.secretaryDto = secretaryDto;
    }

    public AdministrationHistoryDto(Long id, Date startDate, Date endDate, DepartmentDto departmentDto, MemberDto headOfDepartmentDto, MemberDto secretaryDto) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.departmentDto = departmentDto;
        this.headOfDepartmentDto = headOfDepartmentDto;
        this.secretaryDto = secretaryDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public DepartmentDto getDepartmentDto() {
        return departmentDto;
    }

    public void setDepartmentDto(DepartmentDto departmentDto) {
        this.departmentDto = departmentDto;
    }

    public MemberDto getHeadOfDepartmentDto() {
        return headOfDepartmentDto;
    }

    public void setHeadOfDepartmentDto(MemberDto headOfDepartmentDto) {
        this.headOfDepartmentDto = headOfDepartmentDto;
    }

    public MemberDto getSecretaryDto() {
        return secretaryDto;
    }

    public void setSecretaryDto(MemberDto secretaryDto) {
        this.secretaryDto = secretaryDto;
    }
}
