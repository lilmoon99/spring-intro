package ru.lilmoon.hw2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/students")
@RestController
public class StudentController {

    StudentRepository repository;

    @Autowired
    public StudentController(StudentRepository repository) {
        this.repository = repository;
        repository.initStudents();
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return repository.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable long id) {
        return repository.getById(id);
    }

    @GetMapping
    public Student getStudentByName(@RequestParam String name) {
        return repository.getByName(name);
    }

    @GetMapping("/group/{group}")
    public List<Student> getStudentsByGroup(@PathVariable String group) {
        return repository.getStudentsByGroup(group);
    }
    @PostMapping("/new")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student studentToSave = repository.createStudent(student);
        return new ResponseEntity<>(studentToSave, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudentById(@PathVariable long id){
        Student studentToDelete = repository.deleteStudentById(id);
        return new ResponseEntity<>(studentToDelete,HttpStatus.OK);
    }

}
