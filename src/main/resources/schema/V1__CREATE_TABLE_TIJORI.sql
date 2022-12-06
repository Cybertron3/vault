create table tijori (
id UUID not null,
user_id varchar(20) not null,
key varchar(300) not null,
salt varchar(50) not null);