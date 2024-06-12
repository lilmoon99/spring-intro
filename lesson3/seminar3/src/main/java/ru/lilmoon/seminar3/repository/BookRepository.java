package ru.lilmoon.seminar3.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lilmoon.seminar3.entity.BookEntity;
import ru.lilmoon.seminar3.model.Book;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

//    private final List<Book> books;
//
//    public BookRepository() {
//        this.books = new ArrayList<>();
//    }
//
//    @PostConstruct
//    public void generateData() {
//        books.addAll(List.of(
//                new Book("война и мир"),
//                new Book("метрвые души"),
//                new Book("чистый код")
//        ));
//    }
//
//    public List<Book> getAllBook(){
//        return List.copyOf(books);
//    }
//
//    public Book getBookById(long id) {
//        return books.stream().filter(it -> Objects.equals(it.getId(), id))
//                .findFirst()
//                .orElse(null);
//    }
//
//    public void deleteBookById(long id) {
//        books.removeIf(it -> Objects.equals(it.getId(), id));
//    }
//
//    public Book addBook(Book book) {
//        books.add(book);
//        return book;
//    }
}
