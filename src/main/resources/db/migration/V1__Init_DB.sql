create sequence hibernate_sequence start 80 increment 1;

create table brand (
    id int8 not null,
    brand_title varchar(255),
    primary key (id)
);

create table car (
    id int8 not null,
    car_cost float4,
    car_model varchar(255),
    car_status varchar(255),
    city_location varchar(255),
    country_location varchar(255),
    car_image oid,
    car_mileage float8,
    type_engine varchar(255),
    type_transmission varchar(255),
    brand_id int8 not null,
    user_id int8 not null,
    primary key (id)
);

create table user_roles (
    user_id int8 not null,
    roles varchar(255)
);

create table users (
    id int8 not null,
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
);

alter table if exists car
    add constraint car_brand_fk
    foreign key (brand_id) references brand;

alter table if exists car
    add constraint car_user_fk
    foreign key (user_id) references users;

alter table if exists user_roles
    add constraint user_roles_user_fk
    foreign key (user_id) references users;