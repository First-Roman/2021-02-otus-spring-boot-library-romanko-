package ru.otus.library.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenreDTO {
    private long id;
    private String genre;
    private String text;
}
