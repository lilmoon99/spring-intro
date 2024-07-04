package ru.lilmoon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lilmoon.entity.Issue;
import ru.lilmoon.service.IssueService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class IssueController {

    private IssueService service;

    public IssueController(IssueService service) {
        this.service = service;
    }
    @PostMapping("/createIssue")
    public Issue createIssue(){
        return service.issueRandomBookWithRandomReader();
    }
    @GetMapping("/issues")
    public List<Issue> getAllIssues(){
        return service.getAllIssues();
    }
}
