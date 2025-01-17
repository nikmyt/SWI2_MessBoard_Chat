package cz.osu.java.messboardapp.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MessageGeneratorService {
    public MessageGeneratorService()
    {

    }
    public String generateMessage() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
