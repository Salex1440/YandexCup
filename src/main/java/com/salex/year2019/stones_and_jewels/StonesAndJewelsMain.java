package com.salex.year2019.stones_and_jewels;

import java.io.*;

public class StonesAndJewelsMain {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/salex/year2019/stones_and_jewels/input.txt");
        BufferedReader r = new BufferedReader(new FileReader(file));

        String J = r.readLine();
        String S = r.readLine();

        int result = 0;
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (J.indexOf(ch) >= 0) {
                result++;
            }
        }

        System.out.println(result);
    }

}
