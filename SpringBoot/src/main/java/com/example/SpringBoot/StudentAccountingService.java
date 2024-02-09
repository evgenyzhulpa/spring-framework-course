package com.example.SpringBoot;

import com.example.SpringBoot.event.EventHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class StudentAccountingService {

    private final ApplicationEventPublisher publisher;
    private final StudentStorage studentStorage;

    @ShellMethod(key = "p")
    public void print() {
        studentStorage.print();
    }

    @ShellMethod(key = "a")
    public void add(@ShellOption(value = "f") String firstName,
                    @ShellOption(value = "l") String lastName,
                    @ShellOption(value = "a") int age) {
        publisher.publishEvent(new EventHolder(this, studentStorage.add(firstName, lastName, age)));
    }

    @ShellMethod(key = "d")
    public void deleteById(@ShellOption(value = "i") String id) {
        publisher.publishEvent(new EventHolder(this, studentStorage.deleteById(id)));
    }

    @ShellMethod(key = "c")
    public String clear() {
        return studentStorage.clear();
    }
}
