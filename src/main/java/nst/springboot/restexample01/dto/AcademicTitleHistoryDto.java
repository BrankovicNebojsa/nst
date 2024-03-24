package nst.springboot.restexample01.dto;

import java.io.Serializable;
import java.util.Date;

public class AcademicTitleHistoryDto implements Serializable {
    private Long id;
    private Date startDate;
    private Date endDate;
    private MemberDto memberDto;
    private AcademicTitleDto academicTitleDto;
    private ScientificFieldDto scientificFieldDto;

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

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    public void setMemberDto(MemberDto memberDto) {
        this.memberDto = memberDto;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicTitleHistoryDto that = (AcademicTitleHistoryDto) o;
        return startDate.compareTo(that.startDate) == 0 && endDate.compareTo(that.endDate) == 0 && memberDto.equals(that.memberDto) && academicTitleDto.equals(that.academicTitleDto) && scientificFieldDto.equals(that.scientificFieldDto);
    }

}
