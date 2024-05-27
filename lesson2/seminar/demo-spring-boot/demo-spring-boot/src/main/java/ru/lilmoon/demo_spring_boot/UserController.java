package ru.lilmoon.demo_spring_boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    UserRepository repository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        return repository.getAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable long id) {
        return repository.getById(id);
    }

    @GetMapping
    public User getByName(@RequestParam String name) {
        return repository.getByName(name);
    }
}
