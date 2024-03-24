package nst.springboot.restexample01.dto;

import java.io.Serializable;
import java.util.Objects;

public class SubjectDto implements Serializable {
    private Long id;
    private String name;
    private int espb;
    private DepartmentDto departmentDto;

    public SubjectDto() {
    }

    public SubjectDto(Long id, String name, int espb, DepartmentDto departmentDto) {
        this.id = id;
        this.name = name;
        this.espb = espb;
        this.departmentDto = departmentDto;
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

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    public DepartmentDto getDepartmentDto() {
        return departmentDto;
    }

    public void setDepartmentDto(DepartmentDto departmentDto) {
        this.departmentDto = departmentDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectDto that = (SubjectDto) o;
        return espb == that.espb && Objects.equals(name, that.name) && Objects.equals(departmentDto, that.departmentDto);
    }

}
