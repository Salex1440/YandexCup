package com.tinkoff.year2024.summer.teskE;

import java.io.*;

public class TaskE {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/tinkoff/year2024/summer/taskE/input1.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
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
