package com.varun;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

public class WordFinderImpl implements WordFinder {

    private final String sentence;

    public WordFinderImpl(String sentence) {
        this.sentence = sentence;
    }

    @Override
    public Map<Integer, Set<String>> longestWord() {
        Map<Integer, Set<String>> mappedToLength = mapLengthToWords();
        Map<Integer, Set<String>> results = returnIfEmptyMap(mappedToLength);
        if (results != null) return results;
        Integer maxLength = Collections.max(mappedToLength.keySet());
        return getResults(mappedToLength, maxLength);
    }


    @Override
    public Map<Integer, Set<String>> shortestWord() {
        Map<Integer, Set<String>> mappedToLength = mapLengthToWords();
        Map<Integer, Set<String>> results = returnIfEmptyMap(mappedToLength);
        if (results != null) return results;
        Integer minLength = Collections.min(mappedToLength.keySet());
        return getResults(mappedToLength, minLength);
    }

    private Map<Integer, Set<String>> mapLengthToWords() {
        return Arrays.stream(this.sentence.split(" "))
                .map(String::trim)
                .filter(s -> s.length() > 0)
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()));
    }

    private Map<Integer, Set<String>> returnIfEmptyMap(Map<Integer, Set<String>> mappedToLength) {
        if (mappedToLength.isEmpty()) {
            Map<Integer, Set<String>> results = new HashMap<>();
            results.put(0, new HashSet<>(singletonList("")));
            return results;
        }
        return null;
    }

    private Map<Integer, Set<String>> getResults(Map<Integer, Set<String>> map, Integer key) {
        Map<Integer, Set<String>> results = new HashMap<>();
        results.put(key, map.get(key));
        return results;
    }

}
