package edu.hw3;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Task2 {

    private Task2() {
    }

    @SuppressWarnings("MissingSwitchDefault")
    public static ArrayList<String> clusterize(String string) {

        char[] chars = string.toCharArray();

        int openingBraceCount = 0;
        int closingBraceCount = 0;
        ArrayList<Character> currentCluster = new ArrayList<>();
        ArrayList<String> clusters = new ArrayList<>();

        for (char c : chars) {

            switch (c) {
                case '(' -> openingBraceCount++;
                case ')' -> closingBraceCount++;
            }

            currentCluster.add(c);
            if (openingBraceCount != 0 && openingBraceCount == closingBraceCount) {

                clusters.add(currentCluster.stream().map(Object::toString).collect(Collectors.joining("")));
                openingBraceCount = 0;
                closingBraceCount = 0;
                currentCluster = new ArrayList<>();

            }

        }

        if (openingBraceCount != closingBraceCount) {

            throw new IllegalArgumentException(
                "The number of opening and closing brackets should be equal in the argument string");

        } else if (clusters.isEmpty()) {

            clusters.add(currentCluster.stream().map(Object::toString).collect(Collectors.joining("")));

        }

        return clusters;

    }

}
