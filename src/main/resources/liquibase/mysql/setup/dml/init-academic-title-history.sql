insert into tbl_academic_title_history(start_date, end_date, member_id, academic_title_id, scientific_field_id)
values ('2015-01-01', '2017-01-01',
        (select (id) from tbl_member WHERE (first_name = "Dusan" AND last_name = "Savic")),
        (select (id) from tbl_academic_title WHERE name = "Asistent"),
        (select (id) from tbl_scientific_field WHERE name = "Computer Science"));

insert into tbl_academic_title_history(start_date, end_date, member_id, academic_title_id, scientific_field_id)
values ('2017-01-01', '2022-01-01',
        (select (id) from tbl_member WHERE (first_name = "Dusan" AND last_name = "Savic")),
        (select (id) from tbl_academic_title WHERE name = "Docent"),
        (select (id) from tbl_scientific_field WHERE name = "Computer Science"));

insert into tbl_academic_title_history(start_date, member_id, academic_title_id, scientific_field_id)
values ('2022-01-01',
        (select (id) from tbl_member WHERE (first_name = "Dusan" AND last_name = "Savic")),
        (select (id) from tbl_academic_title WHERE name = "Vanredni profesor"),
        (select (id) from tbl_scientific_field WHERE name = "Computer Science"));
