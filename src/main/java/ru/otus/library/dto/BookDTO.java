package ru.otus.library.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BookDTO {

    private long id;

    private long authorId;

    private long genreId;

    private String title;

    private String author;

    private String genre;

}
