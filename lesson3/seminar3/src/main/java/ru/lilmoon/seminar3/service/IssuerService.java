package ru.lilmoon.seminar3.service;

import ru.lilmoon.seminar3.api.IssueRequest;
import ru.lilmoon.seminar3.entity.IssueEntity;

public interface IssuerService {
    IssueEntity issue(IssueRequest request);

    IssueEntity closeIssue(long id);
}
