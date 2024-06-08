package ru.lilmoon.demo_spring_boot;

import lombok.Data;

@Data
public class User {
    private final long id;
    private String name;
    private static long idCounter = 1L;

    public User(String name) {
        this.name = name;
        this.id = idCounter++;
    }
}
