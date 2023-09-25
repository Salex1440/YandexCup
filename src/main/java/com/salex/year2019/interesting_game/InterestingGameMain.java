package com.salex.year2019.interesting_game;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InterestingGameMain {

    public static void main(String[] args) throws IOException {
        int k, n;
        int cntPetya = 0, cntVasya = 0;
        File file = new File("src/main/java/com/salex/year2019/interesting_game/input.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            List<Integer> kn = Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            k = kn.get(0);
            n = kn.get(1);
            int ch, val;
            CharArrayWriter caw = new CharArrayWriter();
            for (int i = 0; i < n; i++) {
                ch = br.read();
                while (!(ch == -1 || ch == 32 || ch == 10)) {
                    caw.append((char)ch);
                    ch = br.read();
                }
                val = Integer.parseInt(caw.toString());
                caw.reset();
                if ((val % 3) == 0 && (val % 5) == 0) {
                    continue;
                } else if ((val % 3) == 0) {
                    cntPetya++;
                } else if ((val % 5) == 0) {
                    cntVasya++;
                }
                if (cntPetya == k || cntVasya == k) break;
            }
            if (cntPetya > cntVasya) {
                System.out.println("Petya");
            } else if (cntPetya < cntVasya) {
                System.out.println("Vasya");
            } else {
                System.out.println("Draw");
            }
        }
    }

}
