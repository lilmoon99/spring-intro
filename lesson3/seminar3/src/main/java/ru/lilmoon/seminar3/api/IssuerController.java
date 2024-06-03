package ru.lilmoon.seminar3.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lilmoon.seminar3.model.BookLimitExceededException;
import ru.lilmoon.seminar3.model.Issue;
import ru.lilmoon.seminar3.repository.IssueRepository;
import ru.lilmoon.seminar3.service.IssuerService;


import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
public class IssuerController {

    @Autowired
    private final IssuerService service;
    @Autowired
    private final IssueRepository repository;


    public IssuerController(IssuerService service, IssueRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
        log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

        final Issue issue;
        try {
            issue = service.issue(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (BookLimitExceededException ex) {
            log.info(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(issue);
    }

    @GetMapping("/{id}")
    public Issue getIssueById(@PathVariable long id) {
        return repository.getIssueById(id);
    }

    @GetMapping
    public List<Issue> getActiveIssues() {
        return repository.getActiveIssues();
    }

    @GetMapping("/history")
    public List<Issue> getAllIssues() {
        return repository.getAllIssues();
    }

    @PutMapping("{id}")
    public void returnBookById(@PathVariable long id) {
        service.closeIssue(id);
    }
}
