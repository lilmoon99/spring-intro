package ru.lilmoon.seminar3.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lilmoon.seminar3.entity.IssueEntity;
import ru.lilmoon.seminar3.entity.ReaderEntity;
import ru.lilmoon.seminar3.repository.IssueRepository;
import ru.lilmoon.seminar3.repository.ReaderRepository;

import java.util.List;
@Service
public class ReaderServiceImpl implements ReaderService{
    @Autowired
    private final ReaderRepository repository;
    @Autowired
    private final IssueRepository issueRepository;

    public ReaderServiceImpl(ReaderRepository repository, IssueRepository issueRepository) {
        this.repository = repository;
        this.issueRepository = issueRepository;
    }

    @Override
    public List<ReaderEntity> getAllReaders() {
        return repository.findAll();
    }

    @Override
    public ReaderEntity getById(long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ReaderEntity createReader(ReaderEntity reader) {
        return repository.save(reader);
    }

    @Override
    public ReaderEntity updateReaderById(long id, ReaderEntity reader) {
        ReaderEntity readerToUpdate = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        readerToUpdate.setName(reader.getName());
        repository.save(readerToUpdate);
        return readerToUpdate;
    }

    @Override
    public void deleteReaderById(long id) {
        repository.deleteById(id);
    }

    public List<IssueEntity> getIssuesByReaderId(long id){
        return issueRepository.findIssuesByReaderId(id);
    }

    @PostConstruct
    private void initTestData(){
        String[] names = {"Aynur","Lisa","Nikolay","Mihail"};

        for (String name : names) {
            ReaderEntity reader = new ReaderEntity();
            reader.setName(name);
            this.createReader(reader);
        }
    }
}
