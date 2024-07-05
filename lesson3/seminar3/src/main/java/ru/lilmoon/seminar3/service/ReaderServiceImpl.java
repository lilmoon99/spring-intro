package ru.lilmoon.seminar3.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lilmoon.seminar3.config.CustomPasswordEncoder;
import ru.lilmoon.seminar3.entity.IssueEntity;
import ru.lilmoon.seminar3.entity.ReaderEntity;
import ru.lilmoon.seminar3.model.Role;
import ru.lilmoon.seminar3.repository.IssueRepository;
import ru.lilmoon.seminar3.repository.ReaderRepository;
import ru.lilmoon.seminar3.service.interfaces.ReaderService;

import java.util.List;
import java.util.Set;

@Service
public class ReaderServiceImpl implements ReaderService {
    @Autowired
    private final ReaderRepository repository;
    @Autowired
    private final IssueRepository issueRepository;
    @Autowired
    private final PasswordEncoder encoder;

    public ReaderServiceImpl(ReaderRepository repository, IssueRepository issueRepository, CustomPasswordEncoder encoder) {
        this.repository = repository;
        this.issueRepository = issueRepository;
        this.encoder = encoder;
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
    public boolean createReader(ReaderEntity reader) {
        ReaderEntity readerFromDB = repository.findByName(reader.getName());
        if (readerFromDB != null){
            return false;
        }
        reader.setRole(Role.USER);
        reader.setPassword(encoder.encode(reader.getPassword()));
        repository.save(reader);
        return true;
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
        ReaderEntity reader1 = ReaderEntity.builder()
                .name("moon")
                .role(Role.ADMIN)
                .password("123")
                .build();
    repository.save(reader1);

        ReaderEntity reader2 = ReaderEntity.builder()
                .name("vienne")
                .role(Role.USER)
                .password("321")
                .build();
        repository.save(reader2);
    }
}
