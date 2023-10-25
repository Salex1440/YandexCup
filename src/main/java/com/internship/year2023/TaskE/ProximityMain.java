package com.internship.year2023.TaskE;

import java.io.*;
import java.util.*;

public class ProximityMain {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/internship/year2023/TaskE/input2.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(readWord(br));
        List<List<Integer>> lists = new ArrayList<>();
        int result = 0;
        for (int i = 0; i < n; i++) {
            int k = Integer.parseInt(readWord(br));
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                list.add(Integer.parseInt(readWord(br)));
            }
            lists.add(list);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                result += getPrefix(lists.get(i), lists.get(j));
            }
        }

        System.out.println(result);

    }

    private static int getPrefix(List<Integer> list1, List<Integer> list2) {
        int cnt = 0;
        int len = Math.min(list1.size(), list2.size());
        for (int i = 0; i < len; i++) {
            if (list1.get(i).equals(list2.get(i))) {
                cnt++;
            } else {
                break;
            }
        }
        return cnt;
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
