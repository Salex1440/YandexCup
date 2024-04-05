package com.internship.year2024.summer.TaskB;

import java.io.*;

public class TaskB {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/internship/year2024/summer/TaskB/input2.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(readWord(br));
        int q = Integer.parseInt(readWord(br));

        String[] dict = new String[n];
        for (int i = 0; i < n; i++) {
            dict[i] = readWord(br);
        }

        for (int i = 0; i < q; i++) {
            int pos = Integer.parseInt(readWord(br));
            String prefix = readWord(br);
            int idx = findFirstPref(prefix, dict);
            if (idx == -1) {
                System.out.println(idx);
            } else {
                if ((idx + pos - 1 < dict.length) && dict[idx + pos - 1].startsWith(prefix)) {
                    System.out.println(idx + pos);
                } else {
                    System.out.println(-1);
                }
            }
        }
    }

    private static int findFirstPref(String pref, String[] dict) {
        int s = 0, e = dict.length - 1;
        int m = (s + e) / 2;
        while (s < e) {
            if (dict[m].compareTo(pref) < 0) {
                s = m + 1;
                m = (s + e) / 2;
            } else if (dict[m].compareTo(pref) > 0) {
                e = m;
                m = (s + e) / 2;
            } else {
                return m;
            }
        }
        if (dict[m].startsWith(pref)) return m;
        return -1;
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
