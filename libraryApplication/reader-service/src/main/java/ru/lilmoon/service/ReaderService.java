package ru.lilmoon.service;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.lilmoon.entity.Reader;
import ru.lilmoon.repository.ReaderRepository;

import java.util.List;
import java.util.Random;

@Service
public class ReaderService {

    private ReaderRepository repository;

    public ReaderService(ReaderRepository repository) {
        this.repository = repository;
    }

    public List<Reader> getAllReaders(){
        return repository.findAll();
    }

    public Reader getRandomReader(){
        List<Reader> readers = repository.findAll();
        return readers.get(new Random().nextInt(readers.size()));
    }

    @PostConstruct
    private void init(){
        Faker faker = new Faker();
        for (int i = 0; i < 25; i++) {
            Reader reader = new Reader();
            reader.setFirstName(faker.name().firstName());
            reader.setLastName(faker.name().lastName());
            reader.setEmail(faker.internet().emailAddress());
            repository.save(reader);
        }
    }
}
