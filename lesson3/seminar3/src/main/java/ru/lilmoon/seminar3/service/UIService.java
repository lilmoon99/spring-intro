package ru.lilmoon.seminar3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.lilmoon.seminar3.entity.IssueEntity;
import ru.lilmoon.seminar3.model.Book;
import ru.lilmoon.seminar3.model.Issue;
import ru.lilmoon.seminar3.model.Reader;
import ru.lilmoon.seminar3.model.UserFriendlyReaderUI;
import ru.lilmoon.seminar3.repository.BookRepository;
import ru.lilmoon.seminar3.repository.IssueRepository;
import ru.lilmoon.seminar3.repository.ReaderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UIService {
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final IssueRepository issueRepository;
    @Autowired
    private final ReaderRepository readerRepository;

    public UIService(BookRepository bookRepository, IssueRepository issueRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.issueRepository = issueRepository;
        this.readerRepository = readerRepository;
    }

    public Model showAllBooks(Model model){
        model.addAttribute("books",bookRepository.findAll());
        return model;
    }

    public Model showAllReaders(Model model){
        model.addAttribute("readers",readerRepository.findAll());
        return model;
    }

    public Model showAllIssues(Model model){
        model.addAttribute("issues",issueRepository.findAll());
        return model;
    }

    public Model showIssuesBooksByReaderId(long id,Model model){
        List<IssueEntity> issueList = issueRepository.findAll().stream().filter(it -> Objects.equals(it.getReaderId(), id)).toList();
        List<UserFriendlyReaderUI> list = new ArrayList<>();
        for (IssueEntity issue : issueList) {
            UserFriendlyReaderUI reader = new UserFriendlyReaderUI(issue.getReaderId(),readerRepository.findById(id).orElseThrow().getName(), bookRepository.findById(issue.getBookId()).orElseThrow().getName());
            list.add(reader);
        }
        model.addAttribute("readersWithBooks",list);
        return model;
    }
}
