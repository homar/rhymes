package com.amazon.siri.rhymes.dictionary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

class Trie {
    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    Trie(TrieNode root) {
        this.root = root;
    }

    void insert(char[] chars) {
        checkArgument(chars);
        ensureInitialization();
        TrieNode node = root;
        int i = 0;
        while(i < chars.length && node.containsChild(chars[i])) {
            node = node.getChild(chars[i]);
            i++;
        }
        if(i == chars.length) {
            return;
        } else {
            if(node.isLeaf()){
                node.stopBeingLeaf();
            }
            while(i < chars.length) {
                node = node.addChild(chars[i]);
                i++;
            }
            node.becameLeaf();
        }
    }

    MatchingPart findClosestString(char[] chars) {
        checkArgument(chars);
        int i = 0;
        TrieNode node = root;
        List<TrieNode> matching = new ArrayList<>();
        while(i < chars.length && node.containsChild(chars[i])) {
            node = node.getChild(chars[i]);
            matching.add(node);
            i++;
        }
        List<TrieNode> path = ifInputWordFoundFindAncestor(matching, chars.length);
        TrieNode lastNode = path.size() > 0 ? path.get(path.size() - 1) : root;
        return new MatchingPart(fromTrieToString(matching), lastNode);
    }

    private List<TrieNode> ifInputWordFoundFindAncestor(List<TrieNode> trieNodes, int lenght) {
        if(trieNodes.size() > 0) {
            int i = trieNodes.size() - 1;
            if (trieNodes.get(i).isLeaf() && trieNodes.get(i).getAllChildren().isEmpty() && trieNodes.size() == lenght) {
                //we found exactly the same word tha we are looking for rhymes for
                return findFirstThatHaveMoreChildren(trieNodes);
            } else {
                return trieNodes;
            }
        }
        return trieNodes;
    }

    private List<TrieNode> findFirstThatHaveMoreChildren(List<TrieNode> trieNodes) {
        int i = trieNodes.size() - 1;
        while(i >= 0) {
            if(trieNodes.get(i).getAllChildren().size() > 1) {
                return trieNodes;
            } else {
                trieNodes.remove(i);
                i--;
            }
        }
        return  trieNodes;
    }

    private String fromTrieToString(List<TrieNode> list) {
        StringBuilder sb = new StringBuilder();
        for(TrieNode t : list) {
            sb.append(t.getLetter());
        }
        return sb.toString();
    }

    Collection<String> findAllStringsInATree() {
        return findAllStringsInATree(root);
    }

    Collection<String> findAllStringsInATree(TrieNode node) {
        Collection<Character> firstLetters = node.getAllChildren();
        return firstLetters.stream()
                .flatMap(firstLetter -> constructAllChildren(firstLetter.toString(), node.getChild(firstLetter), new LinkedList<>()).stream())
                .collect(toList());
    }

    private Collection<String> constructAllChildren(String prefix, TrieNode node, List<String> resultsSoFar) {
        if(node.isLeaf()) {
            resultsSoFar.add(prefix);
        }
        if(node.getAllChildren().isEmpty()) {
            return resultsSoFar;
        } else {
            return node.getAllChildren()
                    .stream()
                    .flatMap(child -> constructAllChildren(prefix + child, node.getChild(child), resultsSoFar).stream())
                    .collect(toSet());
        }
    }

    boolean contains(char[] chars) {
        checkArgument(chars);
        TrieNode node = root;
        int i = 0;
        while(i < chars.length && node.containsChild(chars[i])) {
            node = node.getChild(chars[i]);
            i++;
        }
        if(i == chars.length && node.isLeaf()) {
            return true;
        }
        return false;
    }

    private void ensureInitialization() {
        if(root == null) {
            root = new TrieNode();
        }
    }

    private void checkArgument(char[] chars) {
        if(chars == null || chars.length == 0) {
            throw new IllegalArgumentException("The input string should not be null/empty");
        }
    }

}
