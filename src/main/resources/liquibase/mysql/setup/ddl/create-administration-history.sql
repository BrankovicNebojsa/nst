create table tbl_administration_history
(
    id                    bigint unsigned not null AUTO_INCREMENT,
    start_date            date not null,
    end_date              date,
    department_id         bigint unsigned not null,
    head_of_department_id bigint unsigned not null,
    secretary_id          bigint unsigned not null,
    primary key (id),
    constraint department_administration_history_fk FOREIGN KEY (department_id) REFERENCES tbl_department (id),
    constraint member_head_of_department_fk FOREIGN KEY (head_of_department_id) REFERENCES tbl_member (id),
    constraint member_secretary_fk FOREIGN KEY (secretary_id) REFERENCES tbl_member (id)
)
