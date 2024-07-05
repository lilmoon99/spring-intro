package ru.lilmoon.service;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lilmoon.Entities.Author;
import ru.lilmoon.Entities.Book;
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

    @PostConstruct
    private void init(){
        Faker faker = new Faker();
        for (int i = 0; i < 50; i++) {
            Author author = new Author();
            author.setFirstName(faker.name().firstName());
            author.setLastName(faker.name().lastName());
            Book book = new Book();
            book.setTitle(faker.name().title());
            book.setAuthor(author);
            authorRepository.save(author);
            bookRepository.save(book);
        }
    }
}
