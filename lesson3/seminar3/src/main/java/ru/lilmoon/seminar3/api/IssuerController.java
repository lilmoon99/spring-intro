package ru.lilmoon.seminar3.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lilmoon.seminar3.entity.IssueEntity;
import ru.lilmoon.seminar3.model.BookLimitExceededException;
import ru.lilmoon.seminar3.model.Issue;
import ru.lilmoon.seminar3.repository.IssueRepository;
import ru.lilmoon.seminar3.service.IssueServiceImpl;
import ru.lilmoon.seminar3.service.IssuerServiceImpl;


import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
public class IssuerController {

    @Autowired
    private final IssuerServiceImpl service;
    @Autowired
    private final IssueServiceImpl issueService;

    public IssuerController(IssuerServiceImpl service,IssueServiceImpl issueService) {
        this.service = service;
        this.issueService = issueService;
    }

    @PostMapping
    public ResponseEntity<IssueEntity> issueBook(@RequestBody IssueRequest request) {
        log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

        final IssueEntity issue;
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
    public IssueEntity getIssueById(@PathVariable long id) {
        return issueService.getIssueById(id);
    }

    @GetMapping
    public List<IssueEntity> getAllIssues() {
        return issueService.getAll();
    }

//    @GetMapping("/history")
//    public List<IssueEntity> getAllIssues() {
//        return repository.getAllIssues();
//    }

    @PutMapping("/{id}")
    public IssueEntity returnBookById(@PathVariable long id) {
        return service.closeIssue(id);
    }
}
