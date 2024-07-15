package ru.lilmoon.service;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lilmoon.Entities.Book;
import ru.lilmoon.entity.Issue;
import ru.lilmoon.entity.Reader;
import ru.lilmoon.providers.DataProvider;
import ru.lilmoon.repository.IssueRepository;


import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service

public class IssueService {
    @Autowired
    private DataProvider provider;
    @Autowired
    private IssueRepository repository;
    private final Faker faker = new Faker();

    public IssueService(DataProvider provider, IssueRepository repository) {
        this.provider = provider;
        this.repository = repository;
    }


    public Issue issueRandomBookWithRandomReader() {
        Reader reader = provider.getRandomReader();
        Book book = provider.getRandomBook();
        Issue issue = new Issue();
        issue.setBook(book);
        issue.setReader(reader);
        issue.setIssuedDate(faker.date().between(startOfYear(),endOfYear()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        //        UUID lastInsertedId = repository.getLastInsertedId();
//        return repository.findById(lastInsertedId).orElseThrow(EntityNotFoundException::new);
        return repository.save(issue);
    }

    public List<Issue> getAllIssues(){
        return repository.findAll();
    }

    private Date startOfYear() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, 2024);
        instance.set(Calendar.MONTH, Calendar.JANUARY);
        instance.set(Calendar.DAY_OF_MONTH, 1);
        return instance.getTime();
    }

    private Date endOfYear() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, 2024);
        instance.set(Calendar.MONTH, Calendar.DECEMBER);
        instance.set(Calendar.DAY_OF_MONTH, 31);
        return instance.getTime();
    }
}
