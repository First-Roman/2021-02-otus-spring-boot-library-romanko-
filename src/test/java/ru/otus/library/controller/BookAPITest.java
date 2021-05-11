package ru.otus.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.library.dto.BookDTO;
import ru.otus.library.models.Author;
import ru.otus.library.models.Book;
import ru.otus.library.models.Genre;
import ru.otus.library.sevices.library.LibraryService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookAPI.class)
public class BookAPITest {

    @Autowired
    MockMvc mvc;

    @MockBean
    LibraryService libraryService;

    @Autowired
    ObjectMapper objectMapper;

    final String path = "/api/book/";

    BookDTO bookDTO;
    Book book;
    List<Book> books;

    @BeforeEach
    public void setup() {
        bookDTO = new BookDTO();
        bookDTO.setGenreId(1);
        bookDTO.setAuthorId(1);
        bookDTO.setId(1);
        bookDTO.setGenre("Классика");
        bookDTO.setTitle("Евгений Онегин");
        bookDTO.setAuthor("Пушкин А. С.");
        Author author = new Author();
        author.setLastName("Пушкин");
        author.setMiddleName("Сергеевич");
        author.setFirstName("Александр");
        author.setId(1);
        Genre genre = new Genre();
        genre.setGenreName("Классика");
        genre.setId(1);
        book = new Book();
        book.setId(1);
        book.setTitle("Евгений Онегин");
        book.setAuthor(author);
        book.setGenre(genre);
    }


    @Test
    public void getAllBookTest() throws Exception {
        books = new ArrayList<>();
        books.add(book);
        when(libraryService.getAllBook()).thenReturn(books);
        mvc.perform(get(path + "all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]id")
                        .value("1"));

    }

    @Test
    public void getBookByIdTest() throws Exception {
        long id = 1;
        when(libraryService.getBookById(id)).thenReturn(book);
        mvc.perform(get(path + "{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id")
                        .value("1"));
    }

    @Test
    public void updateBookTest() throws Exception {
        mvc.perform(put(path + "edit")
                .content(objectMapper.writeValueAsBytes(bookDTO))
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("ok"));
    }

    @Test
    public void addBookTest() throws Exception {
        mvc.perform(post(path + "add")
                .contentType(MediaType.MULTIPART_FORM_DATA).content(objectMapper.writeValueAsBytes(bookDTO))).andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("ok"));
    }

    @Test
    public void delBookTest() throws Exception {
        long id = 1;
        mvc.perform(delete(path + "del/{id}", id)
                .contentType(MediaType.MULTIPART_FORM_DATA).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("ok"));
    }

    @Test
    public void updateBookNPETest() throws Exception {
        doThrow(new NullPointerException()).when(libraryService).updateBook(any());
        mvc.perform(put(path + "edit")
                .content(objectMapper.writeValueAsBytes(bookDTO))
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.result").value("bad"));
    }

}
