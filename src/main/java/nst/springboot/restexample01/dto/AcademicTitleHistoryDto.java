package nst.springboot.restexample01.dto;

import java.io.Serializable;
import java.util.Date;

public class AcademicTitleHistoryDto implements Serializable {
    private Long id;
    private final Date startDate;
    private Date endDate;
    private final MemberDto memberDto;
    private final AcademicTitleDto academicTitleDto;
    private final ScientificFieldDto scientificFieldDto;

    public AcademicTitleHistoryDto(Long id, Date startDate, Date endDate, MemberDto memberDto, AcademicTitleDto academicTitleDto, ScientificFieldDto scientificFieldDto) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.memberDto = memberDto;
        this.academicTitleDto = academicTitleDto;
        this.scientificFieldDto = scientificFieldDto;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public MemberDto getMemberDto() {
        return memberDto;
    }

    public AcademicTitleDto getAcademicTitleDto() {
        return academicTitleDto;
    }

    public ScientificFieldDto getScientificFieldDto() {
        return scientificFieldDto;
    }

}
