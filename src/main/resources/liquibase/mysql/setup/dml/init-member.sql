insert into tbl_member(first_name, last_name, department_id, academic_title_id, scientific_field_id, education_title_id)
values ("Dusan", "Savic",
        (select (id) from tbl_department WHERE name = "department-9"),
        (select (id) from tbl_academic_title WHERE name = "Vanredni profesor"),
        (select (id) from tbl_scientific_field WHERE name = "Computer Science"),
        (select (id) from tbl_education_title WHERE name = "Doktorske studije"));

insert into tbl_member(first_name, last_name, department_id, academic_title_id, scientific_field_id, education_title_id)
values ("Tatjana", "Stojanovic",
        (select (id) from tbl_department WHERE name = "department-9"),
        (select (id) from tbl_academic_title WHERE name = "Asistent"),
        (select (id) from tbl_scientific_field WHERE name = "Computer Science"),
        (select (id) from tbl_education_title WHERE name = "Doktorske studije"));

insert into tbl_member(first_name, last_name, department_id, academic_title_id, scientific_field_id, education_title_id)
values ("Petar", "Markovic",
        (select (id) from tbl_department WHERE name = "department-4"),
        (select (id) from tbl_academic_title WHERE name = "Saradnik u nastavi"),
        (select (id) from tbl_scientific_field WHERE name = "Psychology"),
        (select (id) from tbl_education_title WHERE name = "Master studije"));
