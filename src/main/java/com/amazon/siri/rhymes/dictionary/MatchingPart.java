package com.amazon.siri.rhymes.dictionary;

import java.util.Objects;

class MatchingPart {
    private String matching;
    private TrieNode lastMatchingElement;

    public MatchingPart(String matching, TrieNode lastMatchingElement) {
        this.matching = matching;
        this.lastMatchingElement = lastMatchingElement;
    }

    public String getMatching() {
        return matching;
    }

    public TrieNode getLastMatchingElement() {
        return lastMatchingElement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchingPart that = (MatchingPart) o;
        return Objects.equals(matching, that.matching) &&
                Objects.equals(lastMatchingElement, that.lastMatchingElement);
    }

    @Override
    public int hashCode() {

        return Objects.hash(matching, lastMatchingElement);
    }
}
