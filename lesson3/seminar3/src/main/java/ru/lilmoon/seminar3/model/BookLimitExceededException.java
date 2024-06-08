package ru.lilmoon.seminar3.model;

public class BookLimitExceededException extends RuntimeException{
    public BookLimitExceededException(String message) {
        super(message);
    }
}
