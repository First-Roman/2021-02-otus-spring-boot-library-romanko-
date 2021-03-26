create table author
(id number primary key not null,
first_name varchar(50),
last_name varchar(50),
middle_name varchar(50)
);
create table genre
(
id number primary key not null,
genre_name varchar(100)
);
create table book
(
id number primary key not null,
title varchar(100),
author_id number,
genre_id number
);