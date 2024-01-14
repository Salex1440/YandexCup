package com.internship.year2024.TaskA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Tetramino {

    private static final int ROWS = 8, COLS = 8;
    private static boolean[][] board = new boolean[ROWS][COLS];

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/internship/year2024/TaskA/input3.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        fillBoard(br);

        int result = 0;
        for (int i = 1; i < ROWS; i++) {
            for (int j = 1; j < COLS - 1; j++) {
                result = Figure.UP.isFit(i, j) ? result + 1 : result;
            }
        }
        for (int i = 0; i < ROWS - 1; i++) {
            for (int j = 1; j < COLS - 1; j++) {
                result = Figure.DOWN.isFit(i, j) ? result + 1 : result;
            }
        }
        for (int i = 1; i < ROWS - 1; i++) {
            for (int j = 1; j < COLS; j++) {
                result = Figure.LEFT.isFit(i, j) ? result + 1 : result;
            }
        }
        for (int i = 0; i < ROWS - 1; i++) {
            for (int j = 0; j < COLS - 1; j++) {
                result = Figure.RIGHT.isFit(i, j) ? result + 1 : result;
            }
        }

        System.out.println(result);
    }

    public static void fillBoard(BufferedReader br) throws IOException {
        for (int i = 0; i < ROWS; i++) {
            String line = br.readLine();
            for (int j = 0; j < COLS; j++) {
                board[i][j] = line.charAt(j) == '.' ? true : false;
            }
        }
    }

    private enum Figure {
        UP() {
            @Override
            public boolean isFit(int i, int j) {
                if (i == 0 || j == 0 || j == COLS - 1) {
                    return false;
                }
                return board[i][j]
                    && board[i - 1][j]
                    && board[i][j - 1]
                    && board[i][j + 1];
            }
        },
        DOWN() {
            @Override
            public boolean isFit(int i, int j) {
                if (i == ROWS - 1 || j == 0 || j == COLS - 1) {
                    return false;
                }
                return board[i][j]
                    && board[i + 1][j]
                    && board[i][j - 1]
                    && board[i][j + 1];
            }
        },
        LEFT() {
            @Override
            public boolean isFit(int i, int j) {
                if (i == 0 || i == ROWS - 1 || j == 0) {
                    return false;
                }
                return board[i][j]
                    && board[i][j - 1]
                    && board[i - 1][j]
                    && board[i + 1][j];
            }
        },
        RIGHT() {
            @Override
            public boolean isFit(int i, int j) {
                if (i == 0 || i == ROWS - 1 || j == COLS - 1) {
                    return false;
                }
                return board[i][j]
                    && board[i][j + 1]
                    && board[i - 1][j]
                    && board[i + 1][j];
            }
        };

        public abstract boolean isFit(int i, int j);
    }
}
