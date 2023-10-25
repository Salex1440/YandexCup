package com.internship.year2023.TaskB;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DiversityMain {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/internship/year2023/TaskB/input3.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(readWord(br));
        int m = Integer.parseInt(readWord(br));
        int q = Integer.parseInt(readWord(br));
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> cardsA = new HashMap<>();
        Map<Integer, Integer> cardsB = new HashMap<>();
        int diversity = 0;
        for (int i = 0; i < n; i++) {
            diversity = addCard(br, map, cardsA, diversity);
        }
        for (int i = 0; i < m; i++) {
            diversity = addCard(br, map, cardsB, diversity);
        }

        for (int i = 0; i < q; i++) {
            switch (readWord(br)) {
                case "1":
                    switch (readWord(br)) {
                        case "A":
                            diversity = addCard(br, map, cardsA, diversity);
                            System.out.print(diversity + " ");
                            break;
                        case "B":
                            diversity = addCard(br, map, cardsB, diversity);
                            System.out.print(diversity + " ");
                            break;
                    }
                    break;
                case "-1":
                    switch (readWord(br)) {
                        case "A":
                            diversity = removeCard(br, map, cardsA, diversity);
                            System.out.print(diversity + " ");
                            break;
                        case "B":
                            diversity = removeCard(br, map, cardsB, diversity);
                            System.out.print(diversity + " ");
                            break;
                    }
                    break;
            }
        }

        System.out.println();
    }

    private static int addCard(BufferedReader br, Map<Integer, Integer> map, Map<Integer, Integer> cards, int diversity) throws IOException {
        Integer card = Integer.parseInt(readWord(br));
        Integer num = cards.getOrDefault(card, 0);
        cards.put(card, num + 1);
        if (num == 0) {
            Integer div = map.getOrDefault(card, 0);
            diversity = div == 1 ? diversity - 1 : diversity + 1;
            map.put(card, div + 1);
        }
        return diversity;
    }

    private static int removeCard(BufferedReader br, Map<Integer, Integer> map, Map<Integer, Integer> cardsA, int diversity) throws IOException {
        Integer card = Integer.parseInt(readWord(br));
        Integer num = cardsA.get(card);
        cardsA.put(card, num - 1);
        if (num == 1) {
            Integer div = map.get(card);
            map.put(card, div - 1);
            diversity = div == 2 ? diversity + 1 : diversity - 1;
        }
        return diversity;
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
