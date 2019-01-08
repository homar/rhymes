package com.amazon.siri.rhymes.dictionary;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class TrieNode {
    private boolean isLeaf;
    private char letter;
    private Map<Character, TrieNode> children = new HashMap<>();

    TrieNode() {
    }

    TrieNode(char letter) {
        this.letter = letter;
    }

    TrieNode getChild(char c) {
        return children.get(c);
    }

    Collection<Character> getAllChildren() {
        return children.keySet();
    }

    boolean containsChild(char c) {
        return children.containsKey(c);
    }

    TrieNode addChild(char c) {
        return children.compute(c, (k, v) -> new TrieNode(c));
    }

    void becameLeaf() {
        isLeaf = true;
    }

    void stopBeingLeaf() {
        isLeaf = false;
    }

    boolean isLeaf() {
        return isLeaf;
    }

    char getLetter(){
        return letter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrieNode trieNode = (TrieNode) o;
        return isLeaf == trieNode.isLeaf &&
                letter == trieNode.letter &&
                Objects.equals(children, trieNode.children);
    }

    @Override
    public int hashCode() {

        return Objects.hash(isLeaf, letter, children);
    }
}
