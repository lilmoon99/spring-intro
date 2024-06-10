package ru.lilmoon.seminar3.service;

import ru.lilmoon.seminar3.entity.BookEntity;
import ru.lilmoon.seminar3.model.Book;

import java.util.List;

public interface BookService {

    BookEntity getById(long id);

    List<BookEntity> getAll();

    BookEntity createBook(BookEntity book);

    BookEntity updateBook(long id,BookEntity book);

    void deleteBook(long id);
}
