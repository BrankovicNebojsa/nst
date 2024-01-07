package nst.springboot.restexample01.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tbl_administration_history")
public class AdministrationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDate;
    private Date endDate;
    @OneToOne()
    @JoinColumn(name="headOfDepartment_id")
    private Member headOfDepartment;
    @OneToOne()
    @JoinColumn(name="secretary_id")
    private Member secretary;
    @ManyToOne()
    @JoinColumn(name="department_id")
    private Department department;

    public AdministrationHistory() {
    }

    public AdministrationHistory(Date startDate, Date endDate, Member headOfDepartment, Member secretary, Department department) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.headOfDepartment = headOfDepartment;
        this.secretary = secretary;
        this.department = department;
    }

    public AdministrationHistory(Long id, Date startDate, Date endDate, Member headOfDepartment, Member secretary, Department department) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.headOfDepartment = headOfDepartment;
        this.secretary = secretary;
        this.department = department;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
