package ru.lilmoon.seminar3.repository;

import org.springframework.stereotype.Repository;
import ru.lilmoon.seminar3.model.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class IssueRepository {

    private final List<Issue> issues;

    public IssueRepository() {
        this.issues = new ArrayList<>();
    }

    public void save(Issue issue) {
        // insert into ....
        issues.add(issue);
    }

    public Issue getIssueById(long id) {
        return issues.stream().filter(it -> Objects.equals(it.getId(), id)).findFirst().orElse(null);
    }

    public List<Issue> getActiveIssues(){
        return List.copyOf(issues).stream().filter(it -> Objects.equals(it.getReturned_at(),null)).collect(Collectors.toList());
    }

    public List<Issue> getAllIssues(){
        return List.copyOf(issues);
    }

    public List<Issue> getIssuesByReaderId(long id){
        return issues.stream().filter(it -> Objects.equals(it.getReaderId(),id)).collect(Collectors.toList());
    }

}
