package com.global.commtech.test.anagramfinder;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnagramService {

    String outputResult(File file) throws IOException {
        final String FILE_PATH = file.getPath();
        List<String> occurenceWord = new ArrayList<>();

        List<String> words = Files.lines(Paths.get(FILE_PATH))
                .map(line -> line.split("\n")).flatMap(Arrays::stream)
                .collect(Collectors.toList());

        StringBuilder outputString = new StringBuilder();

        List<String> wordsToCompare = words;
        for (String word : words) {
            wordsToCompare.stream().forEach(w -> {
                if (!word.equals(w)) {
                    if (!occurenceWord.contains(word)) {
                        outputString.append(word);
                        occurenceWord.add(word);
                    }
                    isAnagram(word, w, outputString, occurenceWord);
                }
            });
            if (!outputString.toString().endsWith("\n")) {
                outputString.append("\n");
            }
        }
        return outputString.toString();

    }

    static void isAnagram(String currentWord, String nextWord, StringBuilder output, List<String> occ) {

        char[] wordChar = currentWord.toCharArray();
        char[] wChar = nextWord.toCharArray();
        Arrays.sort(wordChar);
        Arrays.sort(wChar);

        if (currentWord.length() == nextWord.length() && Arrays.equals(wordChar, wChar)) {
            if (!occ.contains(nextWord)) {
                output.append(",");
                output.append(nextWord);
                occ.add(nextWord);
            }
        }
    }

}
