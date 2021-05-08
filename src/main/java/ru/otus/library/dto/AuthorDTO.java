package ru.otus.library.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AuthorDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String text;
}
