package nst.springboot.restexample01.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class AcademicTitleDto {
    private Long id;
    @NotNull
    private String name;

    public AcademicTitleDto() {
    }

    public AcademicTitleDto(String name) {
        this.name = name;
    }

    public AcademicTitleDto(Long id, String name) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicTitleDto that = (AcademicTitleDto) o;
        return Objects.equals(name, that.name);
    }

}
