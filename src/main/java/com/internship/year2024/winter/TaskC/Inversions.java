package com.internship.year2024.winter.TaskC;

import java.io.*;

public class Inversions {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/internship/year2024/TaskC/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(readWord(br));
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(readWord(br));
        }
        long sum = 0, cnt = 0;
        for (int i = 0; i < p.length - 1; i++) {
            for (int j = i + 1; j < p.length; j++) {
                swap(p, i, j);
                sum += countInversions(p.clone());
                swap(p, i, j);
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

    private static void swap(int[] p, int i, int j) {
        int t = p[j];
        p[j] = p[i];
        p[i] = t;
    }

//    private static int countInversions(int[] p) {
//        int result = 0;
//        for (int i = 0; i < p.length - 1; i++) {
//            for (int j = i + 1; j < p.length; j++) {
//                if (p[i] > p[j]) result++;
//            }
//        }
//        return result;
//    }

    private static int countInversions(int[] p) {
        if (p.length <= 1) return 0;
        int[] left = (p.length % 2 == 0) ? new int[p.length/2] : new int[p.length/2 + 1];
        System.arraycopy(p, 0, left, 0, left.length);
        int[] right = new int[p.length/2];
        System.arraycopy(p, left.length, right, 0, right.length);
        int leftInv = countInversions(left);
        int rightInv = countInversions(right);
        int splitInv = merge(left, right, p);
        return leftInv + rightInv + splitInv;
    }

    private static int merge(int[] left, int[] right, int[] sorted) {
        int inversions = 0;
        int i = 0, j = 0;
        while (i < left.length && j < right.length) {
            int l = left[i], r = right[j];
            if (l <= r) {
                sorted[i + j] = left[i++];
            } else {
                sorted[i + j] = right[j++];
                inversions += left.length - i;
            }
        }
        while (i < left.length) {
            sorted[i + j] = left[i++];
        }
        while (j < right.length) {
            sorted[i + j] = right[j++];
        }
        return inversions;
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
