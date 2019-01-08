package com.amazon.siri.rhymes;

import com.amazon.siri.rhymes.dictionary.RhymesDictionary;

import java.util.List;
import java.util.Random;

public class RhymeService {

    private RhymesDictionary dictionary;
    private Random random;

    public RhymeService(RhymesDictionary dictionary, Random random) {
        this.dictionary = dictionary;
        this.random = random;
    }

    public void addWordToTheDictionary(String word) {
        String lowerCase = word.toLowerCase();
        dictionary.addWordToTheDictionary(lowerCase);
    }

    public String getRhymeForAWord(String word) {
        String lowerCase = word.toLowerCase();
        List<String> rhymes = dictionary.getRhymingWords(lowerCase);
        rhymes.remove(word);
        if(rhymes.size() > 0) {
            return rhymes.get(random.nextInt(rhymes.size()));
        }
        throw new IllegalArgumentException("There is no rhyme for this word!");
    }
}
