package com.example.SpringBoot;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Student {
    private UUID id;
    private String firstName;
    private String lastName;
    private int age;

    @Override
    public String toString() {
        return "Студент с id " +
                id + ": " + firstName + "\s" +
                lastName + ", " +
                age + " лет";
    }
}
