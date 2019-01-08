package com.amazon.siri.rhymes.dictionary;

import com.amazon.siri.utils.Utils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class RhymesDictionary {

    private Trie trie;

    public RhymesDictionary() {
        trie = new Trie();
    }

    public void addWordToTheDictionary(String word) {
        char[] chars = word.toCharArray();
        char[] reversed = Utils.reverse(chars);
        trie.insert(reversed);
    }

    public List<String> getRhymingWords(String word) {
        char[] chars = word.toCharArray();
        char[] reversed = Utils.reverse(chars);
        MatchingPart matchingCommonPart = trie.findClosestString(reversed);
        if(matchingCommonPart.getMatching().isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        Collection<String> suffixes = trie.findAllStringsInATree(matchingCommonPart.getLastMatchingElement());
        List<String> collect = suffixes.stream()
                .map(suffix -> matchingCommonPart.getMatching() + suffix)
                .map(String::toCharArray)
                .map(Utils::reverse)
                .map(String::new)
                .collect(toList());
        return collect;
    }

    public Collection<String> getAllWords() {
        return trie.findAllStringsInATree()
                .stream()
                .map(String::toCharArray)
                .map(Utils::reverse)
                .map(String::new)
                .collect(toList());
    }
}
