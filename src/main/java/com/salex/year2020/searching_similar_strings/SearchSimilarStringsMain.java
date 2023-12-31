package com.salex.year2020.searching_similar_strings;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
        List<String[]> table = new ArrayList<>();
        List<String[]> tableImp = new ArrayList<>();
        for (int i = 1; i < split.length; i++) {
            impCols.add(Integer.parseInt(split[i]) - 1);
        }
        for (int i = 0; i < n; i++) {
            String[] row = new String[k];
            fillRow(br, row);
            if (isImportantRow(impCols, row)) {
                tableImp.add(row);
            } else {
                table.add(row);
            }
        }

        int result = 0;
        for (int i = 0; i < tableImp.size(); i++) {
            for (int j = i + 1; j < tableImp.size(); j++) {
                if (equalsRows(tableImp.get(i), tableImp.get(j))) {
                    result++;
                }
            }
        }
        for (int i = 0; i < tableImp.size(); i++) {
            for (int j = 0; j < table.size(); j++) {
                if (equalsRows(tableImp.get(i), table.get(j))) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }

    private static void fillRow(BufferedReader br, String[] row) throws IOException {
        int ch, ind = 0;
        CharArrayWriter caw = new CharArrayWriter();
        while ((ch = br.read()) != -1 && (ch != '\n')) {
            if (ch == '»') {
                row[ind++] = caw.toString();
                caw.reset();
            } else {
                caw.append((char)ch);
            }
        }
        if (caw.size() > 0) row[ind] = caw.toString();
        if (row[row.length - 1] == null) row[row.length - 1] = "";
    }

    private static boolean isImportantRow(List<Integer> impCols, String[] row) {
        boolean isImportant = true;
        for (Integer col : impCols) {
            if (row[col].equals("")) {
                isImportant = false;
                break;
            }
        }
        return isImportant;
    }

    private static boolean equalsRows(String[] impRow, String[] row) {
        int cnt = 0;
        for (int i = 0; i < row.length; i++) {
            if (impRow[i].equals("") || row[i].equals("")) {
                continue;
            } else if (!impRow[i].equals(row[i])) {
                return false;
            }
            cnt++;
        }
        return cnt > 0;
    }
}
