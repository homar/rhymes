package com.amazon.siri.rhymes.dictionary

import spock.lang.Specification

class TrieNodeTest extends Specification {

    def "get and add children work correctly"() {
        given:
            def childCharacter = 'c' as char
            def node = new TrieNode()

        when:
            node.addChild(childCharacter)
            def result = node.getChild(childCharacter)

        then:
            result.getLetter() == childCharacter
    }

    def "get children returns null when there is no specific children"() {
        given:
            def node = new TrieNode()

        when:
            def result = node.getChild('c' as char)

        then:
            result == null
    }

    def "containsChild returns true when child exists" () {
        given:
            def node = new TrieNode();
            def charToCheck = 'c' as char
            node.addChild(charToCheck)

        when:
            def result = node.containsChild(charToCheck)

        then:
            result == true
    }

    def "containsChild returns false when specified child doesn't exist" () {
        given:
            def node = new TrieNode();
            def charToCheck = 'c' as char

        when:
            def result = node.containsChild(charToCheck)

        then:
            result == false
    }

    def "becameLeaf correctly sets flag isLeaf to true"() {
        given:
            def node = new TrieNode();

        when:
            node.becameLeaf()
            def result = node.isLeaf()

        then:
            result == true
    }

    def "stopBeingLeaf correctly sets flag isLeaf to false"() {
        given:
            def node = new TrieNode()

        when:
            node.stopBeingLeaf()
            def result = node.isLeaf()

        then:
            result == false
    }

    def "addChild returns added TrieNode"() {
        given:
            def node = new TrieNode()

        when:
            def result = node.addChild('c' as char)

        then:
            result == new TrieNode('c' as char)
    }

    def "getAllChildren returns all chars"() {
        given:
            def node = new TrieNode()
            node.addChild("a" as char)
            node.addChild("b" as char)
            node.addChild("c" as char)
            node.addChild("d" as char)
            node.addChild("z" as char)
        def expected = ["a" as char, "b" as char, "c" as char, "d" as char, "z" as char]

        when:
            def result = node.getAllChildren()
                    .toList()

        then:
            result == expected
    }
}
