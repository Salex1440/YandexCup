package com.salex.year2019.AlarmClocks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateFile {
    public static void main(String[] args) throws IOException {
        String nxk = "100000 100001 1000000000";
        String fileName = "input2.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(nxk);
        writer.newLine();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100_000; i++) {
            sb.append(i+1 + " ");
        }
        writer.write(sb.toString());

        writer.close();
    }
}
