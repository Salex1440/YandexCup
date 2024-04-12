package com.tinkoff.year2024.winter.task3;

import java.io.*;
import java.util.*;

public class Task3 {
    public static void main(String[] args) throws IOException {

        File file = new File("src/main/java/com/tinkoff/year2024/task3/input.txt");
        BufferedReader br = new BufferedReader(new  FileReader(file));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(readWord(br));
        int[] dc = new int[n];
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            dc[i] = Integer.parseInt(readWord(br)) + Integer.parseInt(readWord(br));
            s[i] = Integer.parseInt(readWord(br));
        }
        Arrays.sort(dc);
        Arrays.sort(s);

        int r = 0;
        if (dc[0] > s[0]) {
            System.out.println("NO");
            return;
        }
        for (int i = 1; i < n; i++) {
            if (dc[i] == dc[i - 1]) {
                r++;
            } else {
                r = 0;
            }
            if (dc[i] + r > s[i]) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");

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
