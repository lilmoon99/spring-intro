package ru.lilmoon.seminar3.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@Data
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class Reader {

    public static long sequence = 1L;

    private final long id;
    private final String name;

    public Reader(String name) {
        this(sequence++, name);
    }

}
