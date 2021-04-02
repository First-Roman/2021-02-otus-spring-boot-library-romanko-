insert into author (id,first_name, last_name, middle_name)
values (1,'Лев','Толстой','Николаевич');

insert into genre (id, genre_name)
values (1, 'Классика');

insert into book (id, title, author_id, genre_id)
values (1, 'Война и мир', 1, 1);