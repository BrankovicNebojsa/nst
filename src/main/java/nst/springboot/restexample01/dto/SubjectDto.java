package nst.springboot.restexample01.dto;

import java.io.Serializable;

public class SubjectDto implements Serializable {
    private Long id;
    private String name;
    private int esbp;
    private DepartmentDto departmentDto;

    public SubjectDto() {
    }

    public SubjectDto(Long id, String name, int esbp, DepartmentDto departmentDto) {
        this.id = id;
        this.name = name;
        this.esbp = esbp;
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

    public int getEsbp() {
        return esbp;
    }

    public void setEsbp(int esbp) {
        this.esbp = esbp;
    }

    public DepartmentDto getDepartmentDto() {
        return departmentDto;
    }

    public void setDepartmentDto(DepartmentDto departmentDto) {
        this.departmentDto = departmentDto;
    }


}
