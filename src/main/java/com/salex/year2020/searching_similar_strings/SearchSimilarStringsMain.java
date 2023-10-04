package com.salex.year2020.searching_similar_strings;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchSimilarStringsMain {
    public static void main(String[] args) throws IOException {
        String file = "src/main/java/com/salex/year2020/searching_similar_strings/input.txt";
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        String[] split = line.split(" ");
        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);
        line = br.readLine();
        split = line.split(" ");
        int s = Integer.parseInt(split[0]);
        List<Integer> impCols = new ArrayList<>();
        List<List<String>> table = new ArrayList<>();
        List<List<String>> tableImp = new ArrayList<>();
        for (int i = 1; i < split.length; i++) {
            impCols.add(Integer.parseInt(split[i]) - 1);
        }
        for (int i = 0; i < n; i++) {
            int ch;
            CharArrayWriter caw = new CharArrayWriter();
            List<String> row = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                row.add("");
            }
            int ind = 0;
            boolean isImportant = true;
            while ((ch = br.read()) != '\n' && (ch != -1)) {
                if (ch != '»') {
                    caw.append((char)ch);
                } else {
                    if (isImportant && impCols.contains(ind) && caw.size() == 0) {
                        isImportant = false;
                    }
                    row.set(ind++, caw.toString());
                    caw.reset();
                }
            }
            if (isImportant && impCols.contains(ind) && caw.size() == 0) {
                isImportant = false;
            }
            if (caw.size() > 0) {
                row.set(ind, caw.toString());
            }
            if (isImportant) {
                tableImp.add(row);
            } else {
                table.add(row);
            }
        }

        System.out.println();
    }
}
