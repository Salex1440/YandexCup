package com.internship.year2024.TaskD;

import java.io.*;

public class Rounding {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/internship/year2024/TaskD/input2.txt");
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
        long r = 0;
        for (int i = 0; i < n && x > 0; i++) {
            if (b[i] + r >= sum) {
                c[i] = (b[i] + r) / sum;
                x -= c[i];
                r = (b[i] + r) % sum;
            } else if (b[i] < sum) {
                r += b[i];
            }
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
