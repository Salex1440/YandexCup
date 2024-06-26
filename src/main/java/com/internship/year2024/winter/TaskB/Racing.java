package com.internship.year2024.winter.TaskB;

import java.io.*;

public class Racing {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/internship/year2024/TaskB/input4.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(readWord(br));
        long t = Long.parseLong(readWord(br));
        long s = Long.parseLong(readWord(br));
        long[] v = new long[n];
        for (int i = 0; i < n; i++) {
            v[i] = Long.parseLong(readWord(br));
        }

        long[] c = new long[n];
        long[] r = new long[n];
        for (int i = 0; i < n; i++) {
            c[i] = v[i] * t / s;
            r[i] = v[i] * t % s;
        }
        long result = 0;
        for (int i = 1; i < n; i++) {
            if (c[0] > c[i]) {
                result += c[0] - c[i];
                if (r[0] <= r[i]) result--;
            } else if (c[0] == c[i] && r[0] > r[i]) {
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
