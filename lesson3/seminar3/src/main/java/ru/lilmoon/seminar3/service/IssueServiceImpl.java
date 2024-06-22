package ru.lilmoon.seminar3.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lilmoon.seminar3.entity.IssueEntity;
import ru.lilmoon.seminar3.repository.IssueRepository;
import ru.lilmoon.seminar3.service.interfaces.IssueService;

import java.util.List;
@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    private final IssueRepository repository;

    public IssueServiceImpl(IssueRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<IssueEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public IssueEntity getIssueById(long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public IssueEntity createIssue(IssueEntity issue) {
        return repository.save(issue);
    }

    @Override
    public IssueEntity updateIssue(long id, IssueEntity issue) {
        IssueEntity issueToSave = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        issueToSave.setId(issue.getId());
        issueToSave.setBookId(issue.getBookId());
        issueToSave.setReaderId(issue.getReaderId());
        issueToSave.setIssued_at(issue.getIssued_at());
        issueToSave.setReturned_at(issue.getReturned_at());

        repository.save(issueToSave);
        return issueToSave;
    }

    @Override
    public void deleteIssueById(long id) {
        repository.deleteById(id);
    }
}
