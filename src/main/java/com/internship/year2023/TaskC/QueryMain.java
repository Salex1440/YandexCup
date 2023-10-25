package com.internship.year2023.TaskC;

import java.io.*;
import java.util.*;

public class QueryMain {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/internship/year2023/TaskC/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(readWord(br));
        int m = Integer.parseInt(readWord(br));
        int q = Integer.parseInt(readWord(br));

        Map<String, List<Integer>> table = new HashMap<>();
        List<String> cols = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String col = readWord(br);
            table.put(col, new ArrayList<>());
            cols.add(col);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0 ; j < m; j++) {
                List<Integer> list = table.get(cols.get(j));
                list.add(Integer.parseInt(readWord(br)));
            }
        }

        int result = 0;
        Set<Integer> indexes = new HashSet<>();
        for (int i = 0; i < n; i++) {
            indexes.add(i);
        }
        for (int i = 0; i < q; i++) {
            String colName = readWord(br);
            switch (readWord(br)) {
                case ">":
                    Integer val = Integer.parseInt(readWord(br));
                    List<Integer> list = table.get(colName);
                    Set<Integer> temp = new HashSet<>();
                    for (int ind : indexes) {
                        if (list.get(ind) <= val) {
                            temp.add(ind);
                        }
                    }
                    for (int t : temp) {
                        indexes.remove(t);
                    }
                    break;
                case "<":
                    val = Integer.parseInt(readWord(br));
                    list = table.get(colName);
                    temp = new HashSet<>();
                    for (int ind : indexes) {
                        if (list.get(ind) >= val) {
                            temp.add(ind);
                        }
                    }
                    for (int t : temp) {
                        indexes.remove(t);
                    }
                    break;
            }
        }
        for (Map.Entry<String, List<Integer>> entry : table.entrySet()) {
            for (int ind : indexes) {
                result += entry.getValue().get(ind);
            }
        }
        System.out.println(result);
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
