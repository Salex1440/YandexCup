package com.internship.year2024.winter.TaskD;

import java.io.*;

public class Rounding {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/internship/year2024/TaskD/input5.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(readWord(br));
        long x = Integer.parseInt(readWord(br));
        long[] a = new long[n];
        long[] b = new long[n];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(readWord(br));
            b[i] = x * a[i];
            sum += a[i];
        }

        long[] c = new long[n];
        while (x > 0) {
            long max = 0;
            int ind = 0;
            for (int i = 0; i < n; i++) {
                if (b[i] > max) {
                    ind = i;
                    max = b[i];
                }
            }
            if (b[ind] >= sum) {
                c[ind] += b[ind] / sum;
                b[ind] -= c[ind] * sum;
                x -= c[ind];
            } else {
                c[ind]++;
                b[ind] -= sum;
                x--;
            }
            if (x < 0) c[ind]++;
        }

        for (int i = 0; i < n; i++) {
            System.out.print(c[i] + " ");
        }
        System.out.println();
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
