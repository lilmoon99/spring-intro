package ru.lilmoon.Entities;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

public class BookAndAuthorGenerator {

    private static Faker faker;

    public BookAndAuthorGenerator() {
        faker = new Faker();
    }

    public Book generateRandomBook(){
        Book book = new Book();
        book.setTitle(faker.name().title());
        book.setAuthor(generateRandomAuthor());
        return book;
    }

    public List<Book> generateRandomBook(int quantity){
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            books.add(generateRandomBook());
        }
        return books;
    }

    private Author generateRandomAuthor(){
        Author author = new Author();
        author.setFirstName(faker.name().firstName());
        author.setLastName(faker.name().lastName());
        return author;
    }
}
