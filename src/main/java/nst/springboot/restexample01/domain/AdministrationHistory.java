package nst.springboot.restexample01.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_administration_history")
public class AdministrationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @ManyToOne()
    @JoinColumn(name = "department_id")
    private Department department;
    @ManyToOne()
    @JoinColumn(name = "head_of_department_id")
    private Member headOfDepartment;
    @ManyToOne()
    @JoinColumn(name = "secretary_id")
    private Member secretary;

    public AdministrationHistory() {
    }

    public AdministrationHistory(Date startDate, Date endDate, Department department, Member headOfDepartment, Member secretary) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.department = department;
        this.headOfDepartment = headOfDepartment;
        this.secretary = secretary;
    }

    public AdministrationHistory(Long id, Date startDate, Date endDate, Department department, Member headOfDepartment, Member secretary) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.department = department;
        this.headOfDepartment = headOfDepartment;
        this.secretary = secretary;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Member getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(Member headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    public Member getSecretary() {
        return secretary;
    }

    public void setSecretary(Member secretary) {
        this.secretary = secretary;
    }

}
