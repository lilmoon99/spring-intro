package ru.lilmoon.seminar3.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.lilmoon.seminar3.api.IssueRequest;
import ru.lilmoon.seminar3.entity.IssueEntity;
import ru.lilmoon.seminar3.model.BookLimitExceededException;
import ru.lilmoon.seminar3.service.interfaces.IssuerService;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;


@Slf4j
@Service
@RequiredArgsConstructor
public class IssuerServiceImpl implements IssuerService {

    // спринг это все заинжектит
    private final ReaderServiceImpl readerService;
    private final IssueServiceImpl issueService;
    private final BookServiceImpl bookService;
    @Value("${application.issue.max-allowed-books:1}")
    private int maxBooksInHand;

    /**
     * Проверяет на исключения, затем сохраняет факт выдачи в БД
     * @param request Issue request
     * @return IssueEntity
     */
    @Override
    public IssueEntity issue(IssueRequest request) {

        try {
            bookService.getById(request.getBookId());
        } catch (EntityNotFoundException e) {
            log.info(e.getMessage());
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        try {
            readerService.getById(request.getReaderId());
        } catch (EntityNotFoundException e) {
            log.info(e.getMessage());
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }
        if (isMaxBooksInHand(request)) {
            log.info("Превышено максимальное количество книг у пользователь с идентификатором {}", request.getReaderId());
            throw new BookLimitExceededException("У читателя с идентификатором " + request.getReaderId() + " превышено максимальное количество книг");
        }
        IssueEntity issue = new IssueEntity();
        issue.setBookId(request.getBookId());
        issue.setReaderId(request.getReaderId());
        issue.setIssued_at(LocalDateTime.now());
        issueService.createIssue(issue);
        return issue;

//  public Issue issue(IssueRequest request) {
//    if (bookRepository.findBookById(request.getBookId()) == null) {
//      throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
//    }
//    if (readerRepository.getReaderById(request.getReaderId()) == null) {
//      throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
//    }
//    // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
//    if (isMaxBooksInHand(request)){
//      log.info("Превышено максимальное количество книг у пользователь с идентификатором {}",request.getReaderId());
//      throw new BookLimitExceededException("У читателя с идентификатором "+ request.getReaderId() + " превышено максимальное количество книг");
//
//    }
//    Issue issue = new Issue(request.getBookId(), request.getReaderId());
//    issueRepository.save(issue);
//    return issue;
//  }
//

//
//  public Issue closeIssue(long id){
//    Issue issue = issueRepository.getActiveIssues().stream().filter(it -> Objects.equals(it.getId(), id)).findFirst().orElse(null);
//      assert issue != null;
//      issue.setReturned_at(LocalDateTime.now());
//      return issue;
//  }

    }

    /**
     * Добавляет дату и время возврата книги
     * @param id Идентификатор Issue для закрытия
     * @return IssueEntity
     */
    @Override
    public IssueEntity closeIssue(long id) {
        IssueEntity issueToClose = issueService.getIssueById(id);
        issueToClose.setReturned_at(LocalDateTime.now());
        issueService.updateIssue(id, issueToClose);
        return issueToClose;
    }

    /**
     * Проверка на возможность выдать книгу по максимальному количеству возможных книг на руках
     * @param request Запрос на выдачу
     * @return true - если на руках меньше книг, чем можно взять
     */
    private boolean isMaxBooksInHand(IssueRequest request) {
        return readerService.getIssuesByReaderId(request.getReaderId()).stream().filter(it -> it.getReturned_at() == null).count() >= maxBooksInHand;
    }
}
