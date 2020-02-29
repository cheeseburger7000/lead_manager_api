drop table t_lead if exists;
drop table t_user if exists;

create table t_user (
    id bigint auto_increment,
    username varchar(255),
    email varchar(255) unique,
    password varchar(255),
    primary key (id)
);

create table t_lead (
    id bigint auto_increment,
    name varchar(255),
    email varchar(255) unique,
    message varchar(255),
    create_at timestamp,
    user_id bigint,
    primary key (id)
);

insert into t_user (username, email, password) values ('admin', 'admin@gmail.com', '123');

insert into t_lead (name, email, message, create_at, user_id) values ('espresso', 'espresso@gmail.com', 'Do you like espresso?', now(), 1);
insert into t_lead (name, email, message, create_at, user_id) values ('mocha', 'mocha@gmail.com', 'Do you like mocha?', now(), 1);
insert into t_lead (name, email, message, create_at, user_id) values ('coke', 'coke@gmail.com', 'Do you like coke?', now(), 1);
insert into t_lead (name, email, message, create_at, user_id) values ('latte', 'latte@gmail.com', 'Do you like latte?', now(), 1);
insert into t_lead (name, email, message, create_at, user_id) values ('milk', 'milk@gmail.com', 'Do you like milk?', now(), 1);

