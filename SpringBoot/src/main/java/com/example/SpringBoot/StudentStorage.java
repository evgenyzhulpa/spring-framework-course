package com.example.SpringBoot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StudentStorage {
    private final Set<Student> students;

    public void print() {
        if (students.isEmpty()) {
            System.out.println("Список студентов пуст");
            return;
        }
        students.forEach(System.out::println);
    }

    public String add(String firstName, String lastName, int age) {
        Optional<Student> optional = students.stream()
                .filter(student ->
                        student.getFirstName().equals(firstName)
                                && student.getLastName().equals(lastName)
                                && student.getAge() == age)
                .findAny();
        if (optional.isPresent()) {
            return "Студент с введенными данными уже существует!\n" + optional.get();
        }

        Student student = new Student(UUID.randomUUID(), firstName, lastName, age);
        students.add(student);
        return "Добавлен студент " + student;
    }

    public String deleteById(String id) {
        Optional<Student> optional = students.stream()
                .filter(student -> id.equals(student.getId().toString()))
                .findAny();
        if (optional.isEmpty()) {
            return "Студента с id " + id + " не существует!";
        }
        students.remove(optional.get());
        return "Студент с id " + id + " удален.";
    }

    public String clear() {
        if (!students.isEmpty()) {
            students.clear();
            return "Список студентов очищен";
        }
        return "Список студентов пуст";
    }

}
