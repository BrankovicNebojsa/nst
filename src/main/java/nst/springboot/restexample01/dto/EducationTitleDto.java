package nst.springboot.restexample01.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public class EducationTitleDto implements Serializable {
    private Long id;

    @NotNull
    private String name;

    public EducationTitleDto() {
    }

    public EducationTitleDto(String name) {
        this.name = name;
    }

    public EducationTitleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
