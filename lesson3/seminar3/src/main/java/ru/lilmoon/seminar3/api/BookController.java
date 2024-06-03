package ru.lilmoon.seminar3.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.lilmoon.seminar3.model.Book;
import ru.lilmoon.seminar3.repository.BookRepository;

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
    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return repository.getAllBook();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id) {
        return repository.getBookById(id);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return repository.addBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable long id) {
        repository.deleteBookById(id);
    }
}
