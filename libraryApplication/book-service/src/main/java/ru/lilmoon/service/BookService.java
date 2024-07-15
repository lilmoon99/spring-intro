package ru.lilmoon.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lilmoon.Entities.Author;
import ru.lilmoon.Entities.Book;
import ru.lilmoon.Entities.BookAndAuthorGenerator;
import ru.lilmoon.repository.AuthorRepository;
import ru.lilmoon.repository.BookRepository;

import java.util.List;
import java.util.Random;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private final BookAndAuthorGenerator generator = new BookAndAuthorGenerator();

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Book getRandomBook(){
        List<Book> books = bookRepository.findAll();
        return books.get(new Random().nextInt(books.size()));
    }

    public Book createBook(String title,Author author){
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        if (!authorRepository.findAll().contains(author)){
            authorRepository.save(author);
        }
        if (!bookRepository.findAll().contains(book)){
            book = bookRepository.save(book);
        }
        return book;
    }

    @PostConstruct
    private void init(){
        for (int i = 0; i < 50; i++) {
            Book book = generator.generateRandomBook();
            authorRepository.save(book.getAuthor());
            bookRepository.save(book);
        }
    }
}
