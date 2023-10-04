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
            line = br.readLine();
            for (int start = 0, end = 0; start < line.length(); end++) {
                if (end == line.length() || line.charAt(end) == '»') {
                    row.set(ind++, line.substring(start, end));
                    start = end + 1;
                }
            }
            for (Integer col : impCols) {
                if (row.get(col).equals("")) {
                    isImportant = false;
                    break;
                }
            }
            if (isImportant) {
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

    private static boolean equalsRows(List<String> impRow, List<String> row) {
        for (int i = 0; i < row.size(); i++) {
            if (impRow.get(i).equals("") || row.get(i).equals("")) {
                continue;
            } else if (!impRow.get(i).equals(row.get(i))) {
                return false;
            }
        }
        return true;
    }
}
