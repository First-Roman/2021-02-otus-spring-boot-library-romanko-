insert into authors (id,first_name, last_name, middle_name)
values (1,'Лев','Толстой','Николаевич');

insert into authors (id,first_name, last_name, middle_name)
values (2,'Александр','Пушкин','Сиргеевич');

insert into authors (id,first_name, last_name, middle_name)
values (3,'Александр','Беляев','Романович');


insert into genres (id, genre_name)
values (1, 'Классика');

insert into genres (id, genre_name)
values (2, 'Фантастика');

insert into books (id, title, author_id, genre_id)
values (1, 'Война и мир', 1, 1);

insert into books (id, title, author_id, genre_id)
values (2, 'Евгений Онегин', 2, 1);

insert into books (id, title, author_id, genre_id)
values (3, 'Остров погибших кораблей', 3, 2);

insert into comments (id , comment, nik_name, book_id )
values (1,'GOOD BOOK','DAN',1);

insert into comments (id , comment, nik_name, book_id )
values (2,'Nice!','DAN',1);

insert into comments (id , comment, nik_name, book_id )
values (3,'Завораживающи!','DAN',3);

insert into user (id, name, password)
values (1,'admin','pass');