create table tbl_member
(
    id                  bigint unsigned not null AUTO_INCREMENT,
    first_name           varchar(100) not null,
    last_name            varchar(100) not null,
    department_id       bigint unsigned,
    academic_title_id   bigint unsigned,
    scientific_field_id bigint unsigned,
    education_title_id  bigint unsigned,
    primary key (id),
    constraint department_member_fk FOREIGN KEY (department_id) REFERENCES tbl_department (id),
    constraint academic_title_fk FOREIGN KEY (academic_title_id) REFERENCES tbl_academic_title (id),
    constraint scientific_field_fk FOREIGN KEY (scientific_field_id) REFERENCES tbl_scientific_field (id),
    constraint education_title_fk FOREIGN KEY (education_title_id) REFERENCES tbl_education_title (id)
)