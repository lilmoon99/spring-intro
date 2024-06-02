package ru.lolmoon.seminar3.repository;

import org.springframework.stereotype.Repository;
import ru.lolmoon.seminar3.model.Issue;

import java.util.ArrayList;
import java.util.List;

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

}
