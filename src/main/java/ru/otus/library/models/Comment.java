package ru.otus.library.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document(collection = "comments")
public class Comment {
    @Id
    private String id;

    private String comment;

    private String nikName;

    private String bookId;
}
