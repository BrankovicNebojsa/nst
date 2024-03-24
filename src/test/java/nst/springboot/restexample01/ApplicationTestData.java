package nst.springboot.restexample01;

import nst.springboot.restexample01.dto.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApplicationTestData {
    public static final AcademicTitleDto academicTitle = new AcademicTitleDto(1L, "Professor");
    public static final ScientificFieldDto scientificField = new ScientificFieldDto(1L, "Microelectronics");
    public static final EducationTitleDto educationTitle = new EducationTitleDto(1L, "PhD");
    public static final DepartmentDto department = new DepartmentDto(1L, "CS Department");
    public static final MemberDto member = new MemberDto(1L, "John", "Doe", department, academicTitle, scientificField, educationTitle);
    public static final AcademicTitleHistoryDto academicTitleHistory = new AcademicTitleHistoryDto(1L, parseDate("2021-01-01", "yyyy-MM-dd"), parseDate("2022-01-01", "yyyy-MM-dd"), member, academicTitle, scientificField);
    public static final SubjectDto subject = new SubjectDto(1L, "Intro to Programming", 6, department);
    public static final AdministrationHistoryDto administrationHistory = new AdministrationHistoryDto(1L, parseDate("2021-01-01", "yyyy-MM-dd"), parseDate("2022-01-01", "yyyy-MM-dd"), department, member, member);

    public static Date parseDate(String dateString, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
