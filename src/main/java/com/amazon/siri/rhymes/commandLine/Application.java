package com.amazon.siri.rhymes.commandLine;

import com.amazon.siri.rhymes.RhymeService;
import com.amazon.siri.rhymes.dictionary.RhymesDictionary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class Application {

    public static void main(String[] args) {
        RhymeService rhymeService = new RhymeService(new RhymesDictionary(), new Random());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path to the file with dictionary");
        String path = scanner.next();
        loadDictionary(rhymeService, path);
        String word;
        while (true) {
            System.out.println("Enter word that you want to get a rhyme for");
            word = scanner.next();
            try {
                String rhyme = rhymeService.getRhymeForAWord(word);
                System.out.println(rhyme);
            } catch(IllegalArgumentException e) {
                System.out.println("There is no rhyme for " + word);
            }
        }
    }

    private static void loadDictionary(RhymeService service, String path) {
        try (Stream<String> stream = Files.lines(Paths.get(path.trim()))) {
            stream.forEach(service::addWordToTheDictionary);
        } catch (IOException e) {
            throw new RuntimeException("Problem while reading dictionary file", e);
        }
    }

}
