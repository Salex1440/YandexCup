package com.tinkoff.year2024.winter.task4;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task4 {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/tinkoff/year2024/task4/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(readWord(br));
        int m = Integer.parseInt(readWord(br));
        int g = Integer.parseInt(readWord(br));
        List<Integer> aList = new ArrayList<>(n);
        List<Integer> bList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            aList.add(Integer.parseInt(readWord(br)));
        }
        for (int i = 0; i < n; i++) {
            bList.add(Integer.parseInt(readWord(br)));
        }
        Map<Integer, List<Integer>> friends = new HashMap<>();
        for (int i = 0; i < m; i++) {
            Integer f1 = Integer.parseInt(readWord(br)) - 1;
            Integer f2 = Integer.parseInt(readWord(br)) - 1;
            List<Integer> list = friends.getOrDefault(f1, new ArrayList<>());
            list.add(f2);
            friends.put(f1, list);
            list = friends.getOrDefault(f2, new ArrayList<>());
            list.add(f1);
            friends.put(f2, list);
        }

        System.out.println();

    }

    private static String readWord(BufferedReader br) throws IOException {
        int ch;
        CharArrayWriter caw = new CharArrayWriter();
        while ((ch = br.read()) != ' ' && ch != '\n' && ch != -1) {
            caw.append((char) ch);
        }
        return caw.toString();
    }
}
