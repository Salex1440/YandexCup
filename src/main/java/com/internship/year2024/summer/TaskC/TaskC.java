package com.internship.year2024.summer.TaskC;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class TaskC {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/internship/year2024/summer/TaskC/input1.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(readWord(br));
        int[] a = new int[n];
        int[] b = new int[n];
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        boolean[] c = new boolean[n];
        int result = n;
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(readWord(br));
            b[i] = Integer.parseInt(readWord(br));
            if (setA.contains(a[i]) || setB.contains(b[i])) {
                c[i] = true;
                result--;
            }
            setA.add(a[i]);
            setB.add(b[i]);
        }

        for (int i = 0; i < n; i++) {
            if (c[i]) continue;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (!((a[i] < a[j] && b[i] < b[j]) || (a[i] > a[j] && b[i] > b[j]))) {
                    if (!c[i]) {
                        c[i] = true;
                        result--;
                    }
                    if (!c[j]) {
                        c[j] = true;
                        result--;
                    }
                }
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
