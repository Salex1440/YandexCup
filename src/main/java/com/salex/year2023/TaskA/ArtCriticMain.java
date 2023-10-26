package com.salex.year2023.TaskA;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArtCriticMain {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/salex/year2023/TaskA/input2.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(readWord(br));
        int m = Integer.parseInt(readWord(br));
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(readWord(br));
            if (num > 0) {
                nums.add(num);
            }
        }

        int result = 0;
        for (int i = 0; i < nums.size(); i++) {
            result += Math.pow(nums.get(i), 2);
            for (int j = i + 1, cnt = 0; j < nums.size() && cnt < nums.get(i); j++, cnt++) {
                result += nums.get(j);
            }
        }
        System.out.println(result);
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
