package ru.lilmoon.hw2;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {

    private List<Student> students;
    private final String[] groups = {"basic","java","typescript","python"};

    public StudentRepository(List<Student> students) {
        this.students = new ArrayList<>();
    }

    public void initStudents(){
        for (int i = 1; i < 9; i++) {
            students.add(new Student("student_" + i,groups[new Random().nextInt(0, groups.length)]));
        }
    }

    public Student getById(long id){
        return students.stream().filter(it -> Objects.equals(it.getId(),id)).findFirst().orElse(null);
    }

    public Student getByName(String name){
        return students.stream().filter(it -> Objects.equals(it.getName(),name)).findFirst().orElse(null);
    }

    public List<Student> getAllStudents(){
        return List.copyOf(students);
    }

    public List<Student> getStudentsByGroup(String group){
        return students.stream().filter(it -> Objects.equals(it.getGroupName(),group)).collect(Collectors.toList());
    }

    public Student createStudent(Student student){
        Student studentToSave = new Student();
        studentToSave.setId(student.getId());
        studentToSave.setName(student.getName());
        studentToSave.setGroupName(student.getGroupName());
        if (!students.contains(studentToSave)){
            students.add(studentToSave);
            return studentToSave;
        }
        return null;
    }

    public Student deleteStudentById(long id){
        Student studentToDelete = students.stream().filter(it -> Objects.equals(it.getId(),id)).findFirst().orElse(null);
        if (studentToDelete == null){
            return null;
        }
        students.remove(studentToDelete);
        return studentToDelete;
    }
}
