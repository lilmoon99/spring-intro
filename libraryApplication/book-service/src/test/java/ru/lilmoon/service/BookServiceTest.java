package ru.lilmoon.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.lilmoon.Entities.Author;
import ru.lilmoon.Entities.Book;
import ru.lilmoon.Entities.BookAndAuthorGenerator;
import ru.lilmoon.repository.AuthorRepository;
import ru.lilmoon.repository.BookRepository;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

class BookServiceTest {
    @Mock
    BookRepository bookRepository;
    @Mock
    AuthorRepository authorRepository;
    @Mock
    BookAndAuthorGenerator generator;
    @InjectMocks
    BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(List.of(new Book(new UUID(0L, 0L), "title", new Author(new UUID(0L, 0L), "firstName", "lastName"))));
        when(authorRepository.findAll()).thenReturn(List.of(new Author(new UUID(0L, 0L), "firstName", "lastName")));

        List<Book> result = bookService.getAllBooks();
        Assertions.assertEquals(List.of(new Book(new UUID(0L, 0L), "title", new Author(new UUID(0L, 0L), "firstName", "lastName"))), result);
    }

    @Test
    void testGetAllAuthors() {
        when(authorRepository.findAll()).thenReturn(List.of(new Author(new UUID(0L, 0L), "firstName", "lastName")));

        List<Author> result = bookService.getAllAuthors();

        Assertions.assertEquals(List.of(new Author(new UUID(0L, 0L), "firstName", "lastName")), result);
    }

    @Test
    void testGetRandomBook() {
        when(bookRepository.findAll()).thenReturn(List.of(new Book(new UUID(0L, 0L), "title", new Author(new UUID(0L, 0L), "firstName", "lastName"))));
        when(authorRepository.findAll()).thenReturn(List.of(new Author(new UUID(0L, 0L), "firstName", "lastName")));


        Book result = bookService.getRandomBook();
        Assertions.assertEquals(new Book(new UUID(0L, 0L), "title", new Author(new UUID(0L, 0L), "firstName", "lastName")), result);
    }

    @Test
    void testCreateBook() {
        Book book = new Book();
        book.setTitle("title");
        book.setAuthor(new Author(new UUID(0L, 0L), "firstName", "lastName"));

        when(bookRepository.findAll()).thenReturn(List.of(new Book(new UUID(0L, 0L), "title", new Author(new UUID(0L, 0L), "firstName", "lastName"))));
        when(bookRepository.save(book)).thenReturn(new Book(new UUID(0L, 0L), "title", new Author(new UUID(0L, 0L), "firstName", "lastName")));
        when(authorRepository.findAll()).thenReturn(List.of(new Author(new UUID(0L, 0L), "firstName", "lastName")));

        Book result = bookService.createBook("title", new Author(new UUID(0L, 0L), "firstName", "lastName"));
        Assertions.assertEquals(bookRepository.findAll().stream().findAny().get(), result);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme