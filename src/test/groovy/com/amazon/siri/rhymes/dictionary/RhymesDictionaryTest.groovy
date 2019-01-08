package com.amazon.siri.rhymes.dictionary

import spock.lang.Specification

class RhymesDictionaryTest extends Specification {

    def "new dictionary is empty"() {
        given:
            def dictionary = new RhymesDictionary()

        when:
            def result = dictionary.getAllWords()

        then:
            result == []
    }

    def "insert add words to the dictionary"(String[] words, String[] expected) {
        given:
            def dictionary = new RhymesDictionary()
            words.each { dictionary.addWordToTheDictionary(it) }

        expect:
            def result = dictionary.allWords
            result.size() == expected.size()
            result.toSet() == expected.toList().toSet()

        where:
            words                     | expected
            ["word"]                  | ["word"]
            ["word", "start"]         | ["word", "start"]
            ["word", "start", "stop"] | ["word", "start", "stop"]
            []                        | []
    }

    def "getRhymingWords returns best rhyming words"(String word, String[] expected) {
        given:
            def dictionary = new RhymesDictionary()
            dictionary.addWordToTheDictionary("Computing")
            dictionary.addWordToTheDictionary("Polluting")
            dictionary.addWordToTheDictionary("Diluting")
            dictionary.addWordToTheDictionary("Commuting")
            dictionary.addWordToTheDictionary("Recruting")
            dictionary.addWordToTheDictionary("Drooping")

        expect:
            dictionary.getRhymingWords(word).toSet() == expected.toList().toSet()

        where:
            word          | expected
            "Disputing"   | ["Computing"]
            "Shooting"    | ["Computing", "Polluting", "Diluting", "Commuting", "Recruting"]
            "Convoluting" | ["Polluting", "Diluting"]
            "Orange"      | []


    }
}
