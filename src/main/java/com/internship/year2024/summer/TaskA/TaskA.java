package com.internship.year2024.summer.TaskA;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskA {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/internship/year2024/summer/TaskA/input4.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        long len = getLen(line);
        List<String> words = getWords(line);

        StringBuilder sb = new StringBuilder();
        boolean lineIsEmpty = true;
        for (String word : words) {
            if (sb.length() + 1 + word.length() > len) {
                System.out.println(sb);
                sb.delete(0, sb.length());
                lineIsEmpty = true;
            }
            if (lineIsEmpty) {
                lineIsEmpty = false;
            } else {
                sb.append(" ");
            }
            sb.append(word);
        }
        if (sb.substring(sb.length() - 1).equals(" ")) {
            sb.delete(sb.length() - 1, sb.length());
        }
        System.out.println(sb);
    }

    private static long getLen(String line) {
        long len = 0, cnt = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != ' ' && line.charAt(i) != ',') {
                cnt++;
            } else {
                len = Math.max(len, cnt);
                cnt = 0;
            }
        }
        len = Math.max(len, cnt);
        return 3 * len;
    }

    private static List<String> getWords(String line) {
        List<String> words = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                if (sb.length() != 0) {
                    words.add(sb.toString());
                    sb.delete(0, sb.length());
                }
            } else if (line.charAt(i) == ',') {
                if (line.charAt(i - 1) == ' ') {
                    int lastIdx = words.size() - 1;
                    words.set(lastIdx, words.get(lastIdx) + ",");
                } else {
                    sb.append(",");
                    words.add(sb.toString());
                    sb.delete(0, sb.length());
                }
            } else {
                sb.append(line.charAt(i));
            }
        }
        words.add(sb.toString());
        return words;
    }

}
