package com.tinkoff.year2024.summer.taskA;

import java.io.*;

public class TaskA {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/tinkoff/year2024/summer/taskA/input1.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(readWord(br));
        int[] m = new int[n];
        for (int i = 0; i < n; i++) {
            m[i] = Integer.parseInt(readWord(br));
        }

        int[] result = new int[n];
        int fives = 0, bads = 0;
        for (int i = 0; i < n; i++) {
            if (m[i] == 5) {
                fives++;
            } else if (m[i] == 2 || m[i] == 3) {
                bads++;
            }
            if (i >= 7) {
                if (m[i - 7] == 5) {
                    fives--;
                } else if (m[i - 7] == 2 || m[i - 7] == 3) {
                    bads--;
                }
            }
            if (bads == 0) {
                result[i] = fives;
            } else {
                result[i] = 0;
            }
        }
        int max = result[7];
        for (int i = 7; i < n; i++) {
            max = Math.max(max, result[i]);
        }
        System.out.println(max == 0 ? -1 : max);

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
