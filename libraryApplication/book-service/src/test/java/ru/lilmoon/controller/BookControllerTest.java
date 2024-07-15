package ru.lilmoon.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.lilmoon.Entities.Author;
import ru.lilmoon.Entities.Book;
import ru.lilmoon.Entities.BookAndAuthorGenerator;
import ru.lilmoon.repository.AuthorRepository;
import ru.lilmoon.repository.BookRepository;
import ru.lilmoon.responses.TitleAndAuthorResponse;
import ru.lilmoon.service.BookService;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    private BookAndAuthorGenerator generator;
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BookRepository bookRepository;

    @Test
    void getAllBooks_ReturnsValidResponseEntity() {
        //given

        List<Book> booksExpected = bookRepository.findAll();
        //when

        var result = webTestClient.get()
                .uri("/api/books")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<Book>>() {
                })
                .returnResult().getResponseBody();

        //then

        assertNotNull(result);
        assertEquals(booksExpected.size(), result.size());

        for (Book bookFromResponse : result) {
            boolean found = booksExpected.stream()
                    .filter(it -> Objects.equals(it.getBookId(), bookFromResponse.getBookId()))
                    .anyMatch(it -> Objects.equals(it.getTitle(), bookFromResponse.getTitle()));
            Assertions.assertTrue(found);
        }
    }

    @Test
    void createNewBookTest_ReturnsValidResponseBody() {
        generator = new BookAndAuthorGenerator();
        Book book = generator.generateRandomBook();
        Author author = book.getAuthor();
        String tittle = book.getTitle();

        Book responseBody = webTestClient.post().
                uri("/api/createBook")
                .bodyValue(new TitleAndAuthorResponse(tittle, author))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Book.class)
                .returnResult()
                .getResponseBody();


        assertNotNull(responseBody);
        assertNotNull(responseBody.getBookId());
        assertTrue(bookRepository.findById(responseBody.getBookId()).isPresent());
    }

    @Test
    void randomBookTest_ReturnsValidResponseBody() {
        Book responseBody = webTestClient.get()
                .uri("/api/randomBook")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(responseBody);
        assertTrue(bookRepository.findAll().stream().anyMatch(it-> Objects.equals(it,responseBody)));
    }
}