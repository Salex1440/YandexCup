package com.internship.year2024.summer.TaskD;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class TaskD {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/internship/year2024/summer/TaskD/input2.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(readWord(br));
        String actions = br.readLine();
        Set<Integer> positions = new HashSet<>();
        Set<String> actionSet = new HashSet<>();

        cleanActions("FRRLLRF");

        StringBuilder sb = new StringBuilder();
        sb.append(actions);
        String cleanActions;
        for (int i = 0; i < actions.length(); i++) {
            switch (actions.charAt(i)) {
                case 'R' -> {
                    sb.replace(i, i + 1, "L");
                    cleanActions = cleanActions(sb.toString());
                    if (!actionSet.contains(cleanActions)) {
                        positions.add(getPosition(cleanActions));
                        actionSet.add(cleanActions);
                    }
                    sb.replace(i, i + 1, "F");
                    cleanActions = cleanActions(sb.toString());
                    if (!actionSet.contains(cleanActions)) {
                        positions.add(getPosition(cleanActions));
                        actionSet.add(cleanActions);
                    }
                    sb.replace(i, i + 1, "R");
                }
                case 'L' -> {
                    sb.replace(i, i + 1, "R");
                    cleanActions = cleanActions(sb.toString());
                    if (!actionSet.contains(cleanActions)) {
                        positions.add(getPosition(cleanActions));
                        actionSet.add(cleanActions);
                    }
                    sb.replace(i, i + 1, "F");
                    cleanActions = cleanActions(sb.toString());
                    if (!actionSet.contains(cleanActions)) {
                        positions.add(getPosition(cleanActions));
                        actionSet.add(cleanActions);
                    }
                    sb.replace(i, i + 1, "L");
                }
                case 'F' -> {
                    sb.replace(i, i + 1, "L");
                    cleanActions = cleanActions(sb.toString());
                    if (!actionSet.contains(cleanActions)) {
                        positions.add(getPosition(cleanActions));
                        actionSet.add(cleanActions);
                    }
                    sb.replace(i, i + 1, "R");
                    cleanActions = cleanActions(sb.toString());
                    if (!actionSet.contains(cleanActions)) {
                        positions.add(getPosition(cleanActions));
                        actionSet.add(cleanActions);
                    }
                    sb.replace(i, i + 1, "F");
                }
            }
        }
        System.out.println(positions.size());
    }

    private static String cleanActions(String str) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            if (stack.isEmpty()) {
                if (str.charAt(i) != 'R') {
                    stack.add(str.charAt(i));
                }
            } else {
                switch (str.charAt(i)) {
                    case 'F' -> {
                        stack.addLast(str.charAt(i));
                    }
                    case 'R' -> {
                        if (stack.peekLast() == 'L') {
                            stack.removeLast();
                        } else if (stack.peekLast() == 'F') {
                            stack.addLast(str.charAt(i));
                        }
                    }
                    case 'L' -> {
                        if (stack.peekLast() == 'R') {
                            stack.removeLast();
                        } else if (stack.peekLast() == 'F') {
                            stack.addLast(str.charAt(i));
                        }
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character ch : stack) {
            sb.append(ch);
        }
        return sb.toString();
    }

    private static int getPosition(String actions) {
        int position = 0, direction = 1;
        for (int i = 0; i < actions.length(); i++) {
            switch (actions.charAt(i)) {
                case 'R':
                    direction = 1;
                    break;
                case 'L':
                    direction = -1;
                    break;
                case 'F':
                    position += direction;
                    break;
            }
        }
        return position;
    }

    private static String readWord(BufferedReader br) throws IOException {
        int ch;
        CharArrayWriter caw = new CharArrayWriter();
        while ((ch = br.read()) != ' ' && ch != '\n') {
            caw.append((char) ch);
        }
        return caw.toString();
    }
}
