package com.tinkoff.year2024.task5;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task5 {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/tinkoff/year2024/task5/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(readWord(br));
        Map<Integer, List<Integer>> ways = new HashMap<>();
        Map<Integer, Boolean> visited = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            Integer u = Integer.parseInt(readWord(br));
            Integer v = Integer.parseInt(readWord(br));
            if (!visited.containsKey(u)) visited.put(u, false);
            if (!visited.containsKey(v)) visited.put(v, false);
            List<Integer> list = ways.getOrDefault(u, new ArrayList<>());
            list.add(v);
            ways.put(u, list);
            list = ways.getOrDefault(v, new ArrayList<>());
            list.add(u);
            ways.put(v, list);
        }

        int q = Integer.parseInt(readWord(br));
        for (int i = 0; i < q; i++) {
            int from = Integer.parseInt(readWord(br));
            int to = Integer.parseInt(readWord(br));
            List<Integer> rooms = ways.get(from);
            Map<Integer, Boolean> vis = new HashMap<>(visited);
            vis.put(from, true);
            for (Integer room : rooms) {
                if (isWay(room, to, ways, vis)) {
                    System.out.println(room);
                    break;
                }
            }

        }

        System.out.println();
    }

    private static boolean isWay(int room, int target, Map<Integer, List<Integer>> ways, Map<Integer, Boolean> visited) {
        if (room == target) {
            return true;
        }
        visited.put(room, true);
        List<Integer> list = ways.get(room);
        for (Integer integer : list) {
            if (!visited.get(integer)) {
                if (isWay(integer, target, ways, visited)) return true;
            }
        }
        return false;
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
