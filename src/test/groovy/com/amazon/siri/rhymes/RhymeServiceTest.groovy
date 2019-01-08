package com.amazon.siri.rhymes

import com.amazon.siri.rhymes.dictionary.RhymesDictionary
import spock.lang.Specification

class RhymeServiceTest extends Specification {

    def "getRhyme returns correct rhyme"() {
        given:
            def word = "Computing"
            def service = new RhymeService(new RhymesDictionary(), new Random())
            service.addWordToTheDictionary(word)

        when:
            def result = service.getRhymeForAWord("Disputing")

        then:
            result == word.toLowerCase()
    }

    def "getRhyme is not case sensitive"() {
        given:
            def word = "COmPuTing"
            def service = new RhymeService(new RhymesDictionary(), new Random())
            service.addWordToTheDictionary(word)

        when:
            def result = service.getRhymeForAWord("DisPutIng")

        then:
            result == word.toLowerCase()
    }

    def "getRhyme throws exception when there is no matching rhyme"() {
        given:
            def word = "Computing"
            def service = new RhymeService(new RhymesDictionary(), new Random())
            service.addWordToTheDictionary(word)

        when:
            service.getRhymeForAWord("Orange")

        then:
            IllegalArgumentException ex = thrown()
    }

    def "getRhyme returns correct rhyme when there is many items in dictionary"() {
        given:
            def service = new RhymeService(new RhymesDictionary(), new Random())
            def word = "Computing"
            service.addWordToTheDictionary(word)
            service.addWordToTheDictionary("Recruiting")
            service.addWordToTheDictionary("Drooping")
            service.addWordToTheDictionary("Polluting")
            service.addWordToTheDictionary("Diluting")

        when:
            def result = service.getRhymeForAWord("Disputing")

        then:
            result == word.toLowerCase()
    }

    def "getRhyme returns correct result when there is many possibilities"() {
        given:
            def service = new RhymeService(new RhymesDictionary(), new Random())
            def words = ["Computing", "Recruiting", "Drooping", "Polluting", "Diluting"]
            service.addWordToTheDictionary(words[0])
            service.addWordToTheDictionary(words[1])
            service.addWordToTheDictionary(words[2])
            service.addWordToTheDictionary(words[3])
            service.addWordToTheDictionary(words[4])
            service.addWordToTheDictionary("Orange")

        when:
            def result = service.getRhymeForAWord("Shooting")

        then:
            words.collect { it.toLowerCase() }.contains(result)
    }

}
