package com.amazon.siri.rhymes.dictionary

import spock.lang.Specification

class TrieTest extends Specification {

    private simpleTrieRoot
    private child1
    private child2
    private child3
    private child4
    private TrieNode trie = buildSimpleTrie()

    def "contains returns true when word is in the trie"() {
        given:
            def word = "stop".toCharArray()
            def root = new Trie(trie)

        when:
            def result = root.contains(word)

        then:
            result == true
    }

    def "contains returns false when word is not in the trie"() {
        given:
            def word = "stp".toCharArray()
            def root = new Trie(trie)

        when:
            def result = root.contains(word)

        then:
            result == false
    }

    def "contains returns false when word is not in the trie but there is a word containing the one in question"() {
        given:
            def word = "sto".toCharArray()
            def root = new Trie(trie)

        when:
            def result = root.contains(word)

        then:
            result == false
    }

    def "findClosestString finds nothing if there is only the same string in the trie"() {
        given:
            def word = "stop".toCharArray()
            def root = new Trie(trie)
            def expected = new MatchingPart("", trie)

        when:
            def result = root.findClosestString(word)

        then:
            result == expected
    }

    def "findClosestString correctly stops if there is no children matching next character"() {
        given:
            def word = "store".toCharArray()
            def root = new Trie(trie)
            def expected = new MatchingPart("sto", child3)

        when:
            def result = root.findClosestString(word)

        then:
            result == expected
    }

    def "findClosestString correctly stops if the provided word is longer than the longest word in a trie"() {
        given:
            def word = "stopping".toCharArray()
            def root = new Trie(trie)
            def expected = new MatchingPart("stop", child4)

        when:
            def result = root.findClosestString(word)

        then:
            result == expected
    }

    def "insert correctly adds new word to the tree"() {
        given:
            def word = "start".toCharArray()
            def root = new Trie(trie)

        when:
            root.insert(word)

        then:
            root.contains(word)
    }

    def "insert doesn't fail while adding word that already exist"() {
        given:
            def word = "stop".toCharArray()
            def root = new Trie(trie)

        when:
            root.insert(word)

        then:
            root.contains(word)
    }

    def "inserting new word doesn't corupt already existing"() {
        given:
            def word = "start".toCharArray()
            def root = new Trie(trie)

        when:
            root.insert(word)

        then:
            root.contains("stop".toCharArray())
    }

    def "inserting correctly adds completely new word to the tree"() {
        given:
            def word = "alert".toCharArray()
            def root = new Trie(trie)

        when:
            root.insert(word)

        then:
            root.contains(word)
    }

    def "findAllStringsInATree(node) returns all string in a tree"() {
        given:
            def root = buildComplexTrie()
            def complexTrie = new Trie(root)
            def expected = ["stop", "start", "art"]

        when:
            def result = complexTrie.findAllStringsInATree(root)

        then:
            result.size() == expected.size()
            result.toSet() == expected.toSet()
    }

    def "findAllStringsInATree(node) returns all string in a subtree"() {
        given:
            def root = buildComplexTrie()
            def complexTrie = new Trie(root)
            def expected = ["op", "art"]

        when:
            def result = complexTrie.findAllStringsInATree(root.getChild('s' as char).getChild('t' as char))

        then:
            result.size() == expected.size()
            result.toSet() == expected.toSet()
    }

    def "findAllStringsInATree() returns all string in a tree"() {
        given:
            def root = buildComplexTrie()
            def complexTrie = new Trie(root)
            def expected = ["stop", "start", "art"]

        when:
            def result = complexTrie.findAllStringsInATree()

        then:
            result.size() == expected.size()
            result.toSet() == expected.toSet()
    }

    private def buildSimpleTrie() {
        simpleTrieRoot = new TrieNode()
        child1 = simpleTrieRoot.addChild('s' as char)
        child2 = child1.addChild('t' as char)
        child3 = child2.addChild('o' as char)
        child4 = child3.addChild('p' as char)
        child4.becameLeaf()
        return simpleTrieRoot
    }

    private def buildComplexTrie() {
        def root = new TrieNode()
        def child1 = root.addChild('s' as char)
        def child2 = child1.addChild('t' as char)
        def child3 = child2.addChild('o' as char)
        def child4 = child3.addChild('p' as char)
        child4.becameLeaf()
        def child5 = child2.addChild('a' as char)
        def child6 = child5.addChild('r' as char)
        def child7 = child6.addChild('t' as char)
        child7.becameLeaf()
        def child8 = root.addChild('a' as char)
        def child9 = child8.addChild('r' as char)
        def child10 = child9.addChild('t' as char)
        child10.becameLeaf()
        return root
    }

}
