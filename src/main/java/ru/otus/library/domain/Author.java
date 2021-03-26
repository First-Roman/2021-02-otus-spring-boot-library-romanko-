package ru.otus.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Author {
    private final long id;
    private String firstName;
    private String lastName;
    private String middleName;
}
