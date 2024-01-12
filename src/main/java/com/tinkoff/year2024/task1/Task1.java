package com.tinkoff.year2024.task1;

import java.io.*;

public class Task1 {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/tinkoff/year2024/task1/input.txt");
//        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = readWord(br);

        String search = "code";
        StringBuilder sb = new StringBuilder(line);
        int start = 0, idx = 0;
        while ((idx = sb.substring(start).indexOf(search)) != -1) {
            int i = start + idx + search.length();
            while (i < sb.length() && Character.isDigit(sb.charAt(i))) {
                i++;
            }
            if (i == start + idx + search.length()) {
                start = i;
            } else {
                sb.replace(start + idx, i, "???");
                start += idx + 3;
            }
        }

        System.out.println(sb);

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
