package nst.springboot.restexample01.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tbl_academic_title_history")
public class AcademicTitleHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @ManyToOne()
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne()
    @JoinColumn(name = "academic_title_id")
    private AcademicTitle academicTitle;
    @ManyToOne()
    @JoinColumn(name = "scientific_field_id")
    private ScientificField scientificField;


    public AcademicTitleHistory() {
    }

    public AcademicTitleHistory(Long id, Date startDate, Date endDate, Member member, AcademicTitle academicTitle, ScientificField scientificField) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.member = member;
        this.academicTitle = academicTitle;
        this.scientificField = scientificField;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
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

}
