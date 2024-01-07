package nst.springboot.restexample01.dto;

import jakarta.validation.constraints.NotNull;

public class ScientificFieldDto {
    private Long id;
    @NotNull
    private String name;

    public ScientificFieldDto() {
    }

    public ScientificFieldDto(String name) {
        this.name = name;
    }

    public ScientificFieldDto(Long id, String name) {
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
