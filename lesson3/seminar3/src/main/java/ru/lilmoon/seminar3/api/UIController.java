package ru.lilmoon.seminar3.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.lilmoon.seminar3.service.UIService;

@Controller
@RequestMapping("/ui")
public class UIController {
//    @Autowired
//    private UIService uiService;
//
//    public UIController(UIService uiService) {
//        this.uiService = uiService;
//    }
//
//    @GetMapping("/books")
//    public String showAllBooks(Model model){
//        model = uiService.showAllBooks(model);
//        return "books";
//    }
//
//    @GetMapping("/readers")
//    public String showAllReaders(Model model){
//        model = uiService.showAllReaders(model);
//        return "readers";
//    }
//
//    @GetMapping("/issues")
//    public String showAllIssues(Model model){
//        model = uiService.showAllIssues(model);
//        return "issues";
//    }
//
//    @GetMapping("/reader/{id}")
//    public String showReaderWithBooks(@PathVariable long id, Model model){
//        model = uiService.showIssuesBooksByReaderId(id, model);
//        return "readerWithBooks";
//    }
}
