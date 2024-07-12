package ru.lilmoon.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import static org.mockito.Mockito.*;
class BookServiceTest {
    @Mock
    ru.lilmoon.repository.BookRepository bookRepository;
    @Mock
    ru.lilmoon.repository.AuthorRepository authorRepository;
    @Mock
    ru.lilmoon.Entities.BookAndAuthorGenerator generator;
    @InjectMocks
    ru.lilmoon.service.BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks(){
        when(bookRepository.findAll()).thenReturn(java.util.List.of(new T()));
        when(authorRepository.findAll()).thenReturn(java.util.List.of(new T()));

        java.util.List<ru.lilmoon.Entities.Book> result = bookService.getAllBooks();
        Assertions.assertEquals(java.util.List.of(new ru.lilmoon.Entities.Book(new java.util.UUID(0L, 0L), "title", new ru.lilmoon.Entities.Author(new java.util.UUID(0L, 0L), "firstName", "lastName"))), result);
    }

    @Test
    void testGetAllAuthors(){
        when(bookRepository.findAll()).thenReturn(java.util.List.of(new T()));
        when(authorRepository.findAll()).thenReturn(java.util.List.of(new T()));

        java.util.List<ru.lilmoon.Entities.Author> result = bookService.getAllAuthors();
        Assertions.assertEquals(java.util.List.of(new ru.lilmoon.Entities.Author(new java.util.UUID(0L, 0L), "firstName", "lastName")), result);
    }

    @Test
    void testGetRandomBook(){
        when(bookRepository.findAll()).thenReturn(java.util.List.of(new T()));
        when(authorRepository.findAll()).thenReturn(java.util.List.of(new T()));

        ru.lilmoon.Entities.Book result = bookService.getRandomBook();
        Assertions.assertEquals(new ru.lilmoon.Entities.Book(new java.util.UUID(0L, 0L), "title", new ru.lilmoon.Entities.Author(new java.util.UUID(0L, 0L), "firstName", "lastName")), result);
    }

    @Test
    void testCreateBook(){
        when(bookRepository.findAll()).thenReturn(java.util.List.of(new T()));
        when(bookRepository.save(any(S.class))).thenReturn(new S());
        when(authorRepository.findAll()).thenReturn(java.util.List.of(new T()));
        when(authorRepository.save(any(S.class))).thenReturn(new S());

        ru.lilmoon.Entities.Book result = bookService.createBook("title", new ru.lilmoon.Entities.Author(new java.util.UUID(0L, 0L), "firstName", "lastName"));
        Assertions.assertEquals(new ru.lilmoon.Entities.Book(new java.util.UUID(0L, 0L), "title", new ru.lilmoon.Entities.Author(new java.util.UUID(0L, 0L), "firstName", "lastName")), result);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme