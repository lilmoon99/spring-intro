package ru.lilmoon.controller;

import com.lilmoon.timer.aspect.LogMethodExecTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lilmoon.Entities.Author;
import ru.lilmoon.Entities.Book;
import ru.lilmoon.responses.TitleAndAuthorResponse;
import ru.lilmoon.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/books")
    @LogMethodExecTime
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(service.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/authors")
    @LogMethodExecTime
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(this.service.getAllAuthors());
    }

    @GetMapping("/randomBook")
    public ResponseEntity<Book> getRandomBook() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(this.service.getRandomBook());
    }

    @PostMapping("/createBook")
    public ResponseEntity<Book> createBook(@RequestBody TitleAndAuthorResponse body) {
        Book book = service.createBook(body.getTitle(), body.getAuthor());
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
}
