package ru.lilmoon.seminar3.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lilmoon.seminar3.entity.BookEntity;
import ru.lilmoon.seminar3.model.Book;
import ru.lilmoon.seminar3.repository.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public BookEntity getById(long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<BookEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public BookEntity createBook(BookEntity book) {
        return repository.save(book);
    }

    @Override
    public BookEntity updateBook(long id, BookEntity book) {
        BookEntity bookToUpdate = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        bookToUpdate.setName(book.getName());
        repository.save(bookToUpdate);
        return bookToUpdate;
    }

    @Override
    public void deleteBook(long id) {
        repository.deleteById(id);
    }

    @PostConstruct
    void initTestData(){
        String[] books = {"Чистый код","Грокаем алгоритмы","Чистая архитектура","Полное руководство по Java 8-ое издание"};
        for (String name : books) {
            BookEntity book = new BookEntity();
            book.setName(name);
            this.createBook(book);
        }
    }
}
