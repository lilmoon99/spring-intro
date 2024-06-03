package ru.lilmoon.seminar3.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.lilmoon.seminar3.model.BookLimitExceededException;
import ru.lilmoon.seminar3.model.Issue;
import ru.lilmoon.seminar3.api.IssueRequest;
import ru.lilmoon.seminar3.repository.BookRepository;
import ru.lilmoon.seminar3.repository.IssueRepository;
import ru.lilmoon.seminar3.repository.ReaderRepository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
@Slf4j
@Service
@RequiredArgsConstructor
public class IssuerService {

  // спринг это все заинжектит
  private final BookRepository bookRepository;
  private final ReaderRepository readerRepository;
  private final IssueRepository issueRepository;
  @Value("${application.issue.max-allowed-books:1}")
  private int maxBooksInHand;

  public Issue issue(IssueRequest request) {
    if (bookRepository.getBookById(request.getBookId()) == null) {
      throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    if (readerRepository.getReaderById(request.getReaderId()) == null) {
      throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }
    // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
    if (isMaxBooksInHand(request)){
      log.info("Превышено максимальное количество книг у пользователь с идентификатором {}",request.getReaderId());
      throw new BookLimitExceededException("У читателя с идентификатором "+ request.getReaderId() + " превышено максимальное количество книг");

    }
    Issue issue = new Issue(request.getBookId(), request.getReaderId());
    issueRepository.save(issue);
    return issue;
  }

  private boolean isMaxBooksInHand(IssueRequest request){
    long count = issueRepository.getActiveIssues().stream().filter(it -> Objects.equals(it.getReaderId(), request.getReaderId())).count();

      return count >= maxBooksInHand;
  }

  public Issue closeIssue(long id){
    Issue issue = issueRepository.getActiveIssues().stream().filter(it -> Objects.equals(it.getId(), id)).findFirst().orElse(null);
      assert issue != null;
      issue.setReturned_at(LocalDateTime.now());
      return issue;
  }

}
