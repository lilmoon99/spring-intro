package ru.lilmoon.seminar3.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.lilmoon.seminar3.entity.BookEntity;
import ru.lilmoon.seminar3.model.Book;
import ru.lilmoon.seminar3.repository.BookRepository;
import ru.lilmoon.seminar3.service.BookServiceImpl;

import java.util.List;

/*
 * 1.1 Реализовать контроллер по управлению книгами с ручками:
 * GET /book/{id} - получить описание книги,
 * DELETE /book/{id} - удалить книгу,
 * POST /book - создать книгу
 */
@RestController
@RequestMapping("/book")
@Tag(name = "Books",description = "API to manage a books.")
public class BookController {
    @Autowired
    private final BookServiceImpl service;

    public BookController(BookServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    @Operation(
            summary = "Retrieves an all books",
            description = "Retrieves a list of books from Data Base."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = BookEntity.class)),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",content = {@Content(schema = @Schema())})
    })
    public List<BookEntity> getAllBooks() {
        return service.getAll();
    }

    @Operation(
            summary = "Retrieves the book by :id",
            description = "The response is book object with id,name."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = BookEntity.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",content = {@Content(schema = @Schema())})
    })
    @GetMapping("/{id}")
    public BookEntity getBookById(@PathVariable long id) {
        return service.getById(id);
    }

    @Operation(
            summary = "Creates and adds a new book to DB",
            description = "Creates a new book and adds it to the DB. Requires a json body."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = BookEntity.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",content = {@Content(schema = @Schema())})
    })
    @PostMapping
    public BookEntity addBook(@RequestBody BookEntity book) {
        return service.createBook(book);
    }

    @Operation(
            summary = "Deletes the book by :id",
            description = "Deletes the book from DB by :id."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = BookEntity.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",content = {@Content(schema = @Schema())})
    })
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable long id) {
        service.deleteBook(id);
    }
}
