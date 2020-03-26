package com.varun;

import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

class WordFinderImplTest {

    @Test
    void testDoubleSpacesInMiddleOfSentenceAreIgnored() {
        //Given
        final WordFinder wordFinder = new WordFinderImpl("The   cow jumped   over     the moon");

        //When
        Map<String, Integer> longestWordMap = wordFinder.longestWord();
        Map<String, Integer> shortestWordMap = wordFinder.shortestWord();

        //Then
        assertThat(longestWordMap, IsMapContaining.hasEntry("jumped", 6));
        assertThat(shortestWordMap, IsMapContaining.hasEntry("the", 3));
    }

    @Test
    void testEmptySentenceReturnsZeroLength() {
        //Given
        final WordFinder wordFinder = new WordFinderImpl("");

        //When
        Map<String, Integer> longestWordMap = wordFinder.longestWord();
        Map<String, Integer> shortestWordMap = wordFinder.shortestWord();

        //Then
        assertThat(longestWordMap, IsMapContaining.hasEntry("", 0));
        assertThat(shortestWordMap, IsMapContaining.hasEntry("", 0));
    }

    @Test
    void testDoubleSpacesStartingOfSentenceAreIgnored() {
        //Given
        final WordFinder wordFinder = new WordFinderImpl("      The   cow jumped over   the moon");

        //When
        Map<String, Integer> longestWordMap = wordFinder.longestWord();
        Map<String, Integer> shortestWordMap = wordFinder.shortestWord();

        //Then
        assertThat(longestWordMap, IsMapContaining.hasEntry("jumped", 6));
        assertThat(shortestWordMap, IsMapContaining.hasEntry("the", 3));
    }

    @Test
    void testDoubleSpacesEndOfSentenceAreIgnored() {
        //Given
        final WordFinder wordFinder = new WordFinderImpl("The cow jumped over the moon     ");

        //When
        Map<String, Integer> longestWordMap = wordFinder.longestWord();
        Map<String, Integer> shortestWordMap = wordFinder.shortestWord();

        //Then
        assertThat(longestWordMap, IsMapContaining.hasEntry("jumped", 6));
        assertThat(shortestWordMap, IsMapContaining.hasEntry("the", 3));
    }

    @Test
    void testLongestWordCanBeFound() {
        //Given
        final WordFinder wordFinder = new WordFinderImpl("The cow jumped over the moon");

        //When
        Map<String, Integer> actualResults = wordFinder.longestWord();

        //Then
        assertThat(actualResults, IsMapContaining.hasEntry("jumped", 6));
    }

    @Test
    void testShortestWordCanBeFound() {
        //Given
        final WordFinder wordFinder = new WordFinderImpl("a cow jumped over the moon");

        //When
        Map<String, Integer> actualResults = wordFinder.shortestWord();

        //Then
        assertThat(actualResults, IsMapContaining.hasEntry("a", 1));
    }

}