package com.tinkoff.year2024.summer.taskE;

import java.io.*;

public class TaskE {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/tinkoff/year2024/summer/taskE/input4.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(readWord(br));
        int[][] forest = new int[n][3];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < 3; j++) {
                if (line.charAt(j) == 'W') {
                    forest[i][j] = -1;
                } else if (line.charAt(j) == '.') {
                    forest[i][j] = 0;
                } else if (line.charAt(j) == 'C') {
                    forest[i][j] = 1;
                }
            }
        }

        System.out.println(getResult(forest, n));
    }

    private static int getResult(int[][] forest, int n) {
        int res = 0;
        for (int i = 1; i < n; i++) {
            boolean left = checkPath(forest, i, 0);
            boolean forward = checkPath(forest, i, 1);
            boolean right = checkPath(forest, i, 2);
            if (!left && !forward && !right) break;
            for (int j = 0; j < 3; j++) {
                res = Math.max(forest[i][j], res);
            }
        }
        return res;
    }

    private static boolean checkPath(int[][] forest, int toI, int toJ) {
        if (forest[toI][toJ] == -1) {
            return false;
        }
        if (toJ == 0) {
            if (forest[toI - 1][toJ] == -1 && forest[toI - 1][toJ + 1] == -1) return false;
        } else if (toJ == 1) {
            if (forest[toI - 1][toJ - 1] == -1 && forest[toI - 1][toJ] == -1 && forest[toI - 1][toJ + 1] == -1) return false;
        } else if (toJ == 2) {
            if (forest[toI - 1][toJ - 1] == -1 && forest[toI - 1][toJ] == -1) return false;
        }
        if (forest[toI][toJ] == 0) {
            if (toJ == 0) {
                forest[toI][toJ] = Math.max(forest[toI - 1][toJ], forest[toI][toJ]);
                forest[toI][toJ] = Math.max(forest[toI - 1][toJ + 1], forest[toI][toJ]);
            } else if (toJ == 1) {
                forest[toI][toJ] = Math.max(forest[toI - 1][toJ - 1], forest[toI][toJ]);
                forest[toI][toJ] = Math.max(forest[toI - 1][toJ], forest[toI][toJ]);
                forest[toI][toJ] = Math.max(forest[toI - 1][toJ + 1], forest[toI][toJ]);
            } else if (toJ == 2) {
                forest[toI][toJ] = Math.max(forest[toI - 1][toJ - 1], forest[toI][toJ]);
                forest[toI][toJ] = Math.max(forest[toI - 1][toJ], forest[toI][toJ]);
            }
        } else if (forest[toI][toJ] > 0) {
            if (toJ == 0) {
                forest[toI][toJ] = Math.max(forest[toI - 1][toJ] + 1, forest[toI][toJ]);
                forest[toI][toJ] = Math.max(forest[toI - 1][toJ + 1] + 1, forest[toI][toJ]);
            } else if (toJ == 1) {
                forest[toI][toJ] = Math.max(forest[toI - 1][toJ - 1] + 1, forest[toI][toJ]);
                forest[toI][toJ] = Math.max(forest[toI - 1][toJ] + 1, forest[toI][toJ]);
                forest[toI][toJ] = Math.max(forest[toI - 1][toJ + 1] + 1, forest[toI][toJ]);
            } else if (toJ == 2) {
                forest[toI][toJ] = Math.max(forest[toI - 1][toJ - 1] + 1, forest[toI][toJ]);
                forest[toI][toJ] = Math.max(forest[toI - 1][toJ] + 1, forest[toI][toJ]);
            }
        }
        return true;
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
