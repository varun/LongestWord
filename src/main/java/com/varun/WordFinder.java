package com.varun;

import java.util.Map;
import java.util.Set;

public interface WordFinder {
    Map<Integer, Set<String>> longestWord();
    Map<Integer, Set<String>> shortestWord();
}
