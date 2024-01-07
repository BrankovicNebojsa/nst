package nst.springboot.restexample01.domain;

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

    public AcademicTitle(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
