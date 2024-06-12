package ru.lilmoon.seminar3.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.lilmoon.seminar3.entity.IssueEntity;
import ru.lilmoon.seminar3.entity.ReaderEntity;
import ru.lilmoon.seminar3.model.Issue;
import ru.lilmoon.seminar3.model.Reader;
import ru.lilmoon.seminar3.repository.IssueRepository;
import ru.lilmoon.seminar3.repository.ReaderRepository;
import ru.lilmoon.seminar3.service.IssueServiceImpl;
import ru.lilmoon.seminar3.service.ReaderServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    private final ReaderServiceImpl readerService;


    public ReaderController(ReaderServiceImpl readerService) {
        this.readerService = readerService;
    }

    @GetMapping
    public List<ReaderEntity> getAllReaders() {
        return readerService.getAllReaders();
    }

    @GetMapping("/{id}")
    public ReaderEntity getReaderById(@PathVariable long id) {
        return readerService.getById(id);
    }

    @PostMapping
    public ReaderEntity addReader(@RequestBody ReaderEntity reader) {
        return readerService.createReader(reader);
    }

    @DeleteMapping("/{id}")
    public void deleteReaderById(@PathVariable long id) {
        readerService.deleteReaderById(id);
    }

    @GetMapping("/{id}/issue")
    public List<IssueEntity> getIssuesByReaderId(@PathVariable long id) {
        return readerService.getIssuesByReaderId(id);
    }
}
