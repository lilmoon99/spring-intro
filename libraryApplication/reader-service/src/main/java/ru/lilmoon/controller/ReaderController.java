package ru.lilmoon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lilmoon.enity.Reader;
import ru.lilmoon.service.ReaderService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReaderController {
    @Autowired
    ReaderService service;

    public ReaderController(ReaderService service) {
        this.service = service;
    }

    @GetMapping("/readers")
    public List<Reader> getAllReaders() {
        return service.getAllReaders();
    }

    @GetMapping("/randomReader")
    public Reader getRandomReader(){
        return service.getRandomReader();
    }
}
