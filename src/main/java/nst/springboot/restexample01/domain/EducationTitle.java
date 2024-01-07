package nst.springboot.restexample01.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_education_title")
public class EducationTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public EducationTitle() {
    }

    public EducationTitle(String name) {
        this.name = name;
    }

    public EducationTitle(Long id, String name) {
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
