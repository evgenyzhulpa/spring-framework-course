package org.example;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeSet;

@Component
public class ContactService {
    @Getter
    @Setter
    private TreeSet<Contact> contacts = new TreeSet<>();
    @Value("${app.contact-data-file.path}")
    private String contactDataFilePath;
    @Value("${contact-storage-file.path}")
    private String contactStorageFilePath;

    public void runService() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Список команд:\n" +
                    "\tprint - вывести все контакты;\n" +
                    "\tadd - добавить новый контакт;\n" +
                    "\tdelete_by_email - удалить контакт по email;\n" +
                    "\tsave_to_file - сохранить все контакты в файл;\n" +
                    "\texit - выйти из приложения\n" +
                    "Выберите команду:\t");
            Scanner scanner = new Scanner(System.in);
            try {
                isRunning = runCommand(scanner.nextLine());
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            }
        }
    }

    private boolean runCommand(String command) throws FileNotFoundException {
        switch (command) {
            case("print") -> printContacts();
            case ("add") -> addNewContact();
            case("delete_by_email") -> deleteContactByEmail();
            case ("save_to_file") -> saveContactsToFile();
            case ("exit") -> {
                return false;
            }
            default -> System.out.println("Неверная команда!");
        }
        return true;
    }

    private void printContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Список контактов пуст.");
        }
        contacts.forEach(System.out::println);
    }

    private void addNewContact() {
        System.out.println("Введите данные в формате Ф.И.О.; номер телефона; адрес электронной почты:\t");
        Scanner scanner = new Scanner(System.in);
        String data = scanner.nextLine();
        String regExp = "([А-Яа-я]+(\s)*){3}; \\+8[0-9]{8}; [A-Za-z0-9]+@[A-Za-z]+.[a-z]+";
        if (!data.matches(regExp)) {
            System.out.println("Данные введены некорректно!");
            addNewContact();
            return;
        }
        contacts.add(addNewContact(data, "; "));
        System.out.println("Контакт успешно добавлен!");
    }

    private void deleteContactByEmail() {
        System.out.println("Введите адрес электронной почты:\t");
        Scanner scanner = new Scanner(System.in);
        String email = scanner.nextLine();
        Optional<Contact> optional = contacts.stream()
                .filter(contact -> contact.getEmail().equals(email))
                .findAny();
        if (!optional.isPresent()) {
            System.out.println("Контакта с адресом эл. почты " + email +" нету в списке.");
            deleteContactByEmail();
            return;
        }
        contacts.remove(optional.get());
        System.out.println("Контакт успешно удален!");
    }

    private void saveContactsToFile() throws FileNotFoundException {
        File file = new File(contactStorageFilePath);
        PrintWriter printWriter = new PrintWriter(file.getPath());
        for (Contact contact : contacts) {
            printWriter.write(String.format("%s;\s%s;\s%s\n", contact.getFullName(), contact.getPhoneNumber(), contact.getEmail()));
        }
        printWriter.flush();
        printWriter.close();
        System.out.println("Данные выгружены успешно.");
    }
    public Contact addNewContact(String data, String regex) {
        String[] dataArray = data.split(regex);
        return new Contact(dataArray[0], dataArray[1], dataArray[2]);
    }

}
