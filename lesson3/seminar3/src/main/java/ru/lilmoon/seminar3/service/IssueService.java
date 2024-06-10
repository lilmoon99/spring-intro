package ru.lilmoon.seminar3.service;

import ru.lilmoon.seminar3.entity.IssueEntity;

import java.util.List;

public interface IssueService {

    List<IssueEntity> getAll();

    IssueEntity getIssueById(long id);

    IssueEntity createIssue(IssueEntity issue);

    IssueEntity updateIssue(long id, IssueEntity issue);

    void deleteIssueById(long id);
}
