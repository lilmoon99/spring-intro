package ru.lilmoon.seminar3.api;

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
public class BookController {
    @Autowired
    private final BookServiceImpl service;

    public BookController(BookServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<BookEntity> getAllBooks() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public BookEntity getBookById(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping
    public BookEntity addBook(@RequestBody BookEntity book) {
        return service.createBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable long id) {
        service.deleteBook(id);
    }
}
