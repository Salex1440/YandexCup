package com.internship.year2024.summer.TaskC;

import java.io.*;

public class TaskC {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/internship/year2024/summer/TaskC/input2.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(readWord(br));
        int[] a = new int[n];
        int[] b = new int[n];
        boolean[] c = new boolean[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(readWord(br));
            b[i] = Integer.parseInt(readWord(br));
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((a[i] < a[j] && b[i] < b[j]) || (a[i] > a[j] && b[i] > b[j])) {
                } else {
                    c[i] = true;
                    c[j] = true;
                }
            }
        }
        long result = 0;
        for (int i = 0; i < n; i++) {
            if (!c[i]) {
                result++;
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
