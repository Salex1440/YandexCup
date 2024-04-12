package com.tinkoff.year2024.winter.task2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task2 {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/tinkoff/year2024/task2/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(readWord(br));
        List<Integer> books = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            books.add(Integer.parseInt(readWord(br)));
        }
        Map<Integer, Long> map = books.stream()
            .collect(Collectors.groupingBy(i -> i, Collectors.counting()));

        System.out.println(map.size());
        map.values().stream()
            .sorted().forEach(i -> System.out.print(i + " "));
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
