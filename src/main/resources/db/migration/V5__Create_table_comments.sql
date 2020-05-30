create table comment (
    id int8 not null,
    comment_text varchar(1000) not null,
    car_id int8 not null,
    user_id int8 not null,
    receiver_comment_id int8 null,
    primary key (id)
);

alter table if exists comment
    add constraint comment_comment_fk
    foreign key (receiver_comment_id) references comment;