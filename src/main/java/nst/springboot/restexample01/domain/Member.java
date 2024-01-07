package nst.springboot.restexample01.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToOne
    @JoinColumn(name = "academic_title_id")
    private AcademicTitle academicTitle;
    @OneToOne
    @JoinColumn(name = "education_title_id")
    private EducationTitle educationTitle;
    @OneToOne
    @JoinColumn(name = "scientific_field_id")
    private ScientificField scientificField;

    public Member() {
    }

    public Member(String firstName, String lastName, AcademicTitle academicTitle,
                  EducationTitle educationTitle, ScientificField scientificField) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.academicTitle = academicTitle;
        this.educationTitle = educationTitle;
        this.scientificField = scientificField;
    }
}
