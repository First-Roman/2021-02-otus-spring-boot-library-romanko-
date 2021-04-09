-- drop table authors;
-- drop table genres;
-- drop table books;
-- drop table comments;

create table authors
(id number auto_increment primary key not null ,
first_name varchar(50),
last_name varchar(50),
middle_name varchar(50)
);

create table genres
(
id number auto_increment primary key not null,
genre_name varchar(100)
);


create table books
(
id number auto_increment primary key not null,
title varchar(100),
author_id number,
genre_id number,
constraint fk_genre_id foreign key (genre_id) references genres(id) on delete cascade ,
constraint fk_author_id foreign key (author_id) references authors(id) on delete cascade
);

create table comments
(
id number auto_increment primary key not null,
comment varchar(500),
nik_name varchar(50),
book_id number,
constraint fk_book_id foreign key (book_id) references books(id) on delete cascade
);