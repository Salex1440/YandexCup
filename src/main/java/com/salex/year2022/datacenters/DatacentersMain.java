package com.salex.year2022.datacenters;

import java.io.*;
import java.util.Arrays;

public class DatacentersMain {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/salex/year2022/datacenters/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        String[] split = line.split(" ");
        int n = Integer.parseInt(split[0]); // number of datacenters
        int m = Integer.parseInt(split[1]); // number of servers in a datacenter
        int q = Integer.parseInt(split[2]);
        int[] R = new int[n];
        int[] A = new int[n];
        boolean[][] servers = new boolean[n][m];
        int[] value = new int[n];
        Arrays.fill(A, m);
        int i, j, maxI = 0, minI = 0;
        for (int c = 0; c < q; c++) {
            switch (readWord(br)) {
                case "RESET":
                    i = Integer.parseInt(readWord(br)) - 1;
                    A[i] = m;
                    R[i]++;
                    for (int k = 0; k < m; k++) {
                        servers[i][k] = true;
                    }
                    value[i] = A[i] * R[i];
                    maxI = getMaxInd(value, n);
                    minI = getMinInd(value, n);
                    break;
                case "DISABLE":
                    i = Integer.parseInt(readWord(br)) - 1;
                    j = Integer.parseInt(readWord(br)) - 1;
                    if (servers[i][j] == true) {
                        servers[i][j] = false;
                        A[i]--;
                        value[i] = A[i] * R[i];
                        maxI = getMaxInd(value, n);
                        minI = getMinInd(value, n);
                    }
                    break;
                case "GETMAX":
                    System.out.println(maxI + 1);
                    break;
                case "GETMIN":
                    System.out.println(minI + 1);
                    break;
            }
        }

    }

    private static String readWord(BufferedReader br) throws IOException {
        int ch;
        CharArrayWriter caw = new CharArrayWriter();
        while ((ch = br.read()) != ' ' && ch != '\n') {
            caw.append((char)ch);
        }
        return caw.toString();
    }


    public static int getMaxInd(int[] value, int n) {
        int maxI = 0;
        for (int k = 0; k < n; k++) {
            maxI = (value[k] > value[maxI]) ? k : maxI;
        }
        return maxI;
    }

    public static int getMinInd(int[] value, int n) {
        int minI = 0;
        for (int k = 0; k < n; k++) {
            minI = (value[k] < value[minI]) ? k : minI;
        }
        return minI;
    }

}
