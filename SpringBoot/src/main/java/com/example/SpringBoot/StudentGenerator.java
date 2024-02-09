package com.example.SpringBoot;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class StudentGenerator {
    
    private final StudentStorage studentStorage;
    private final int minRandomNum = 1;
    private final int maxRandomNum = 20;

    @PostConstruct
    public void generate() {
        Random random = new Random();
        int randomNum = random.nextInt(minRandomNum, maxRandomNum);

        for (int i = 1; i <= randomNum; i++) {
            String lastName = "Иванов " + i;
            String firstName = "Иван";
            studentStorage.add(firstName, lastName, i + maxRandomNum);
        }
    }
    
}
