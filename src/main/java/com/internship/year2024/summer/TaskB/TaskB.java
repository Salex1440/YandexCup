package com.internship.year2024.summer.TaskB;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.IOException;

public class TaskB {

    public static void main(String[] args) {

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