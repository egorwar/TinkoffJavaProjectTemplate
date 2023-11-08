package edu.hw3;

import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.Collectors;

public class Task2 {

    private Task2() {
    }

    @SuppressWarnings("MissingSwitchDefault")
    public static ArrayList<String> clusterize(String string) {

        char[] chars = string.toCharArray();

        ArrayList<Character> currentCluster = new ArrayList<>();
        ArrayList<String> clusters = new ArrayList<>();
        Stack<Character> stack = new Stack<>();

        for (char c : chars) {

            if (!stack.isEmpty() && stack.peek() == getOther(c)) {
                stack.pop();
            } else {
                stack.push(c);
            }

            currentCluster.add(c);
            if (stack.isEmpty()) {

                clusters.add(currentCluster.stream().map(Object::toString).collect(Collectors.joining("")));
                currentCluster = new ArrayList<>();

            }

        }

        if (!stack.isEmpty()) {

            throw new IllegalArgumentException(
                "The number of opening and closing brackets should be equal in the argument string");

        } else if (clusters.isEmpty()) {

            clusters.add(currentCluster.stream().map(Object::toString).collect(Collectors.joining("")));

        }

        return clusters;

    }

    private static char getOther(char c) {
        return c == '(' ? ')' : '(';
    }

}
