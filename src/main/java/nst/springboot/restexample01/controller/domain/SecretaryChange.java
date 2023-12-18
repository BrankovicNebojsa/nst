package nst.springboot.restexample01.controller.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tbl_secretary_change")
public class SecretaryChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDate;
    private Date endDate;
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public SecretaryChange() {
    }

    public SecretaryChange(Date startDate, Date endDate, Member member) {
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
