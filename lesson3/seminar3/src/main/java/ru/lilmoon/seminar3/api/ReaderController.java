package ru.lilmoon.seminar3.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.lilmoon.seminar3.model.Issue;
import ru.lilmoon.seminar3.model.Reader;
import ru.lilmoon.seminar3.repository.IssueRepository;
import ru.lilmoon.seminar3.repository.ReaderRepository;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    private final ReaderRepository readerRepository;
    @Autowired
    private final IssueRepository issueRepository;

    public ReaderController(ReaderRepository readerRepository,IssueRepository issueRepository) {
        this.readerRepository = readerRepository;
        this.issueRepository = issueRepository;
    }

    @GetMapping
    public List<Reader> getAllReaders(){
        return readerRepository.getAllReaders();
    }

    @GetMapping("/{id}")
    public Reader getReaderById(@PathVariable long id){
        return readerRepository.getReaderById(id);
    }

    @PostMapping
    public Reader addReader(@RequestBody Reader reader){
        return readerRepository.addReader(reader);
    }

    @DeleteMapping("/{id}")
    public void deleteReaderById(@PathVariable long id){
        readerRepository.deleteReaderById(id);
    }

    @GetMapping("/{id}/issue")
    public List<Issue> getIssuesByReaderId(@PathVariable long id){
        return issueRepository.getIssuesByReaderId(id);
    }
}
