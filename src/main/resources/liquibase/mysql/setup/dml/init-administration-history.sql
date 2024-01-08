insert into tbl_administration_history(start_date, end_date, department_id, head_of_department_id, secretary_id)
values ('2021-01-01', '2021-12-31',
        (select (id) from tbl_department WHERE name = "department-9"),
        (select (id) from tbl_member WHERE (first_name = "Dusan" AND last_name = "Savic")),
        (select (id) from tbl_member WHERE (first_name = "Tatjana" AND last_name = "Stojanovic")));

insert into tbl_administration_history(start_date, end_date, department_id, head_of_department_id, secretary_id)
values ('2022-01-01', '2022-12-31', (select (id) from tbl_department WHERE name = "department-9"),
        (select (id) from tbl_member WHERE (first_name = "Petar" AND last_name = "Markovic")),
        (select (id) from tbl_member WHERE (first_name = "Tatjana" AND last_name = "Stojanovic")));

insert into tbl_administration_history(start_date, department_id, head_of_department_id, secretary_id)
values ('2023-01-01', (select (id) from tbl_department WHERE name = "department-9"),
        (select (id) from tbl_member WHERE (first_name = "Tatjana" AND last_name = "Stojanovic")),
        (select (id) from tbl_member WHERE (first_name = "Petar" AND last_name = "Markovic")));
