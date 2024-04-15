package com.tinkoff.year2024.summer.taskD;

import java.io.*;

public class TaskD {
    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/tinkoff/year2024/summer/taskD/input3.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(readWord(br));
        if (n == 1) System.out.println(0);
        Direction d = Direction.valueOf(readWord(br));
        long[][] arr = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(readWord(br));
            }
        }

        d.rotate(arr);

        System.out.println();



    }

    private static String readWord(BufferedReader br) throws IOException {
        int ch;
        CharArrayWriter caw = new CharArrayWriter();
        while ((ch = br.read()) != ' ' && ch != '\n') {
            caw.append((char) ch);
        }
        return caw.toString();
    }

    private static enum Direction {
        R {
            @Override
            public void rotate(long[][] arr) {
                int n = arr.length;
                long t;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n - 1 - i; j++) {
                        t = arr[i][j];
                        arr[i][j] = arr[n - 1 - j][n - 1 - i];
                        arr[n - 1 - j][n - 1 - i] = t;
                        int x1 = i, y1 = j, x2 = n - 1 - j, y2 = n - 1 - i;
                        System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
                    }
                }
                for (int i = 0; i < n / 2; i++) {
                    for (int j = 0; j < n; j++) {
                        t = arr[i][j];
                        arr[i][j] = arr[n - 1 - i][j];
                        arr[n - 1 - i][j] = t;
                        int x1 = i, y1 = j, x2 = n - 1 - i, y2 = j;
                        System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
                    }
                }
            }
        },
        L {
            @Override
            public void rotate(long[][] arr) {
                int n = arr.length;
                long t;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n - 1 - i; j++) {
                        t = arr[i][j];
                        arr[i][j] = arr[n - 1 - j][n - 1 - i];
                        arr[n - 1 - j][n - 1 - i] = t;
                        int x1 = i, y1 = j, x2 = n - 1 - j, y2 = n - 1 - i;
                        System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
                    }
                }
                for (int j = 0; j < n / 2; j++) {
                    for (int i = 0; i < n; i++) {
                        t = arr[i][j];
                        arr[i][j] = arr[i][n - 1 - j];
                        arr[i][n - 1 - j] = t;
                        int x1 = i, y1 = j, x2 = i, y2 = n - 1 - j;
                        System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
                    }
                }
            }

        };

        public abstract void rotate(long[][] arr);
    }
}
