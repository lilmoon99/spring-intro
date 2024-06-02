package ru.lolmoon.seminar3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lolmoon.seminar3.api.IssueRequest;
import ru.lolmoon.seminar3.model.Issue;
import ru.lolmoon.seminar3.repository.BookRepository;
import ru.lolmoon.seminar3.repository.IssueRepository;
import ru.lolmoon.seminar3.repository.ReaderRepository;


import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssuerService {

  // спринг это все заинжектит
  private final BookRepository bookRepository;
  private final ReaderRepository readerRepository;
  private final IssueRepository issueRepository;

  public Issue issue(IssueRequest request) {
    if (bookRepository.getBookById(request.getBookId()) == null) {
      throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    if (readerRepository.getReaderById(request.getReaderId()) == null) {
      throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }
    // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)

    Issue issue = new Issue(request.getBookId(), request.getReaderId());
    issueRepository.save(issue);
    return issue;
  }

}
