package nst.springboot.restexample01.controller.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_academic_title")
public class AcademicTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public AcademicTitle() {
    }

    public AcademicTitle(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
