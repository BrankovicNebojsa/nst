package nst.springboot.restexample01.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_scientific_field")
public class ScientificField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public ScientificField() {
    }

    public ScientificField(String name) {
        this.name = name;
    }

    public ScientificField(Long id, String name) {
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
