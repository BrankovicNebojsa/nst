package nst.springboot.restexample01.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @ManyToOne()
    @JoinColumn(name = "department_id")
    private Department department;
    @OneToOne
    @JoinColumn(name = "academic_title_id")
    private AcademicTitle academicTitle;
    @OneToOne
    @JoinColumn(name = "scientific_field_id")
    private ScientificField scientificField;
    @OneToOne
    @JoinColumn(name = "education_title_id")
    private EducationTitle educationTitle;


    public Member() {
    }

    public Member(String firstName,
                  String lastName,
                  Department department,
                  AcademicTitle academicTitle,
                  ScientificField scientificField,
                  EducationTitle educationTitle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.academicTitle = academicTitle;
        this.scientificField = scientificField;
        this.educationTitle = educationTitle;
    }

    public Member(Long id,
                  String firstName,
                  String lastName,
                  Department department,
                  AcademicTitle academicTitle,
                  ScientificField scientificField,
                  EducationTitle educationTitle) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.academicTitle = academicTitle;
        this.scientificField = scientificField;
        this.educationTitle = educationTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AcademicTitle getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(AcademicTitle academicTitle) {
        this.academicTitle = academicTitle;
    }

    public EducationTitle getEducationTitle() {
        return educationTitle;
    }

    public void setEducationTitle(EducationTitle educationTitle) {
        this.educationTitle = educationTitle;
    }

    public ScientificField getScientificField() {
        return scientificField;
    }

    public void setScientificField(ScientificField scientificField) {
        this.scientificField = scientificField;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


}
