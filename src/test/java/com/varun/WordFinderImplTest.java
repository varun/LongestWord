package com.varun;

import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;

class WordFinderImplTest {

    @Test
    void testDoubleSpacesInMiddleOfSentenceAreIgnored() {
        //Given
        final WordFinder wordFinder = new WordFinderImpl("The   cow jumped   over     the moon");

        //When
        Map<Integer, Set<String>> longestWordMap = wordFinder.longestWord();
        Map<Integer, Set<String>> shortestWordMap = wordFinder.shortestWord();

        //Then
        assertThat(longestWordMap, IsMapContaining.hasEntry(6, new HashSet<>(singletonList("jumped"))));
        assertThat(shortestWordMap, IsMapContaining.hasEntry(3, new HashSet<>(asList("The", "the", "cow"))));
    }

    @Test
    void testEmptySentenceReturnsZeroLength() {
        //Given
        final WordFinder wordFinder = new WordFinderImpl("");

        //When
        Map<Integer, Set<String>> longestWordMap = wordFinder.longestWord();
        Map<Integer, Set<String>> shortestWordMap = wordFinder.shortestWord();

        //Then
        assertThat(longestWordMap, IsMapContaining.hasEntry(0, new HashSet<>(singletonList(""))));
        assertThat(shortestWordMap, IsMapContaining.hasEntry(0, new HashSet<>(singletonList(""))));
    }

    @Test
    void testDoubleSpacesStartingOfSentenceAreIgnored() {
        //Given
        final WordFinder wordFinder = new WordFinderImpl("      The   cow jumped over   the moon");

        //When
        Map<Integer, Set<String>> longestWordMap = wordFinder.longestWord();
        Map<Integer, Set<String>> shortestWordMap = wordFinder.shortestWord();

        //Then
        assertThat(longestWordMap, IsMapContaining.hasEntry(6, new HashSet<>(singletonList("jumped"))));
        assertThat(shortestWordMap, IsMapContaining.hasEntry(3, new HashSet<>(asList("The", "the", "cow"))));
    }

    @Test
    void testDoubleSpacesEndOfSentenceAreIgnored() {
        //Given
        final WordFinder wordFinder = new WordFinderImpl("The cow jumped over the moon     ");

        //When
        Map<Integer, Set<String>> longestWordMap = wordFinder.longestWord();
        Map<Integer, Set<String>> shortestWordMap = wordFinder.shortestWord();

        //Then
        assertThat(longestWordMap, IsMapContaining.hasEntry(6, new HashSet<>(singletonList("jumped"))));
        assertThat(shortestWordMap, IsMapContaining.hasEntry(3, new HashSet<>(asList("The", "the", "cow"))));
    }

    @Test
    void testLongestWordCanBeFound() {
        //Given
        final WordFinder wordFinder = new WordFinderImpl("The cow jumped over the moon");

        //When
        Map<Integer, Set<String>> longestWordMap = wordFinder.longestWord();

        //Then
        assertThat(longestWordMap, IsMapContaining.hasEntry(6, new HashSet<>(singletonList("jumped"))));

    }

    @Test
    void testShortestWordCanBeFound() {
        //Given
        final WordFinder wordFinder = new WordFinderImpl("a cow jumped over the moon");

        //When
        Map<Integer, Set<String>> shortestWordMap = wordFinder.shortestWord();

        //Then
        assertThat(shortestWordMap, IsMapContaining.hasEntry(1, new HashSet<>(singletonList("a"))));
    }

}