create table tbl_academic_title_history
(
    id                  bigint unsigned not null AUTO_INCREMENT,
    start_date          date not null,
    end_date            date,
    member_id           bigint unsigned not null,
    academic_title_id   bigint unsigned not null,
    scientific_field_id bigint unsigned not null,
    primary key (id),
    constraint member_academic_title_history_fk FOREIGN KEY (member_id) REFERENCES tbl_member (id),
    constraint academic_title_academic_title_history_fk FOREIGN KEY (academic_title_id) REFERENCES tbl_academic_title (id),
    constraint scientific_field_academic_title_history_fk FOREIGN KEY (scientific_field_id) REFERENCES tbl_scientific_field (id)
)
