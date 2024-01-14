package com.internship.year2024.TaskC;

import java.io.*;

public class Inversions {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/internship/year2024/TaskC/input3.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(readWord(br));
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(readWord(br));
        }
        long sum = 0, cnt = 0;

        for (int i = 0; i < p.length - 1; i++) {
            for (int j = i + 1; j < p.length; j++) {
                sum += moveAndCount(p.clone(), i, j);
                cnt++;
            }
        }

        long d = gcd(sum, cnt);

        System.out.println(sum / d + "/" + cnt / d);
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    private static int moveAndCount(int[] p, int i, int j) {
        int t = p[j];
        p[j] = p[i];
        p[i] = t;
        return countInversions(p);
    }

    private static int countInversions(int[] p) {
        int result = 0;
        for (int i = 0; i < p.length - 1; i++) {
            for (int j = i + 1; j < p.length; j++) {
                if (p[i] > p[j]) result++;
            }
        }
        return result;
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
