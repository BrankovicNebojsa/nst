package nst.springboot.restexample01.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tbl_academic_title_history")
public class AcademicTitleHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDate;
    private Date endDate;
    @OneToOne
    @JoinColumn(name = "academic_title_id")
    private AcademicTitle academicTitle;
    @OneToOne
    @JoinColumn(name = "scientific_field_id")
    private ScientificField scientificField;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public AcademicTitleHistory() {
    }

    public AcademicTitleHistory(Long id, Date startDate, Date endDate, AcademicTitle academicTitle, ScientificField scientificField, Member member) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.academicTitle = academicTitle;
        this.scientificField = scientificField;
        this.member = member;
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

    public AcademicTitle getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(AcademicTitle academicTitle) {
        this.academicTitle = academicTitle;
    }

    public ScientificField getScientificField() {
        return scientificField;
    }

    public void setScientificField(ScientificField scientificField) {
        this.scientificField = scientificField;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
