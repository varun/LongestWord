package com.varun;

import java.util.*;
import java.util.stream.Collectors;

public class WordFinderImpl implements WordFinder {

    private final String sentence;

    public WordFinderImpl(String sentence) {
        this.sentence = sentence;
    }

    @Override
    public Map<String, Integer> longestWord() {
        Set<String> words = toSet(this.sentence.trim().split("\\s+"));
        Optional<String> optionalLongestWord = words.stream().map(String::toLowerCase).max(Comparator.comparingInt(String::length));
        return getResults(optionalLongestWord);
    }

    @Override
    public Map<String, Integer> shortestWord() {
        Set<String> words = toSet(this.sentence.trim().split("\\s+"));
        Optional<String> optionalShortestWord = words.stream().map(String::toLowerCase).min(Comparator.comparingInt(String::length));
        return getResults(optionalShortestWord);
    }

    private Set<String> toSet(String[] words) {
        return Arrays.stream(words).map(String::trim).collect(Collectors.toSet());
    }

    private Map<String, Integer> getResults(Optional<String> optionalLongestWord) {
        Map<String, Integer> results = new HashMap<>();

        if (optionalLongestWord.isPresent()) {
            String word = optionalLongestWord.get();
            results.put(word, word.length());
        } else {
            results.put("", 0);
        }
        return results;
    }


}
