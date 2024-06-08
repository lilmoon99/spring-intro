package ru.lilmoon.hw2;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Student {
    private long id;
    private static long idCounter = 1L;
    private String name;
    private String groupName;

    public Student(String name, String groupName) {
        this.id = idCounter++;
        this.name = name;
        this.groupName = groupName;
    }

    public Student() {
    }
}
