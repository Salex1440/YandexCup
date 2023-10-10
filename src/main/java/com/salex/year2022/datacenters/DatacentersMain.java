package com.salex.year2022.datacenters;

import java.io.*;
import java.util.*;

public class DatacentersMain {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/salex/year2022/datacenters/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(readWord(br)); // number of datacenters
        int m = Integer.parseInt(readWord(br)); // number of servers in a datacenter
        int q = Integer.parseInt(readWord(br));
        int[] R = new int[n];
        int[] A = new int[n];
        Map<Integer, Set<Integer>> servers = new HashMap<>();
        for (int i = 0; i < n; i++) {
            servers.put(i, new HashSet<>());
        }
        int[] value = new int[n];
        Arrays.fill(A, m);
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int i, j, maxI = 0, minI = 0;
        for (int c = 0; c < q; c++) {
            switch (readWord(br)) {
                case "RESET":
                    i = Integer.parseInt(readWord(br)) - 1;
                    int prev = value[i];
                    A[i] = m;
                    R[i]++;
                    servers.put(i, new HashSet<>());
                    int val = A[i] * R[i];
                    value[i] = val;
                    if (!treeMap.containsKey(val) || treeMap.get(val) > i) {
                        treeMap.put(val, i);
                        if (val != prev) {
                            treeMap.remove(prev);
                        }
                    }
                    maxI = getMaxInd(value, n);
                    minI = getMinInd(value, n);
                    break;
                case "DISABLE":
                    i = Integer.parseInt(readWord(br)) - 1;
                    j = Integer.parseInt(readWord(br)) - 1;
                    prev = value[i];
                    if (!servers.get(i).contains(j)) {
                        servers.get(i).add(j);
                        A[i]--;
                        val = A[i] * R[i];
                        value[i] = val;
                        if (!treeMap.containsKey(val) || treeMap.get(val) > i) {
                            treeMap.put(val, i);
                            if (val != prev) {
                                treeMap.remove(prev);
                            }
                        }
                        maxI = getMaxInd(treeMap);
                        minI = getMinInd(value, n);
                    }
                    break;
                case "GETMAX":
                    System.out.println(getMaxInd(treeMap) + 1);
                    System.out.println(maxI + 1);
                    break;
                case "GETMIN":
                    System.out.println(-1);
                    System.out.println(minI + 1);
                    break;
            }
        }

    }

    private static String readWord(BufferedReader br) throws IOException {
        int ch;
        CharArrayWriter caw = new CharArrayWriter();
        while ((ch = br.read()) != ' ' && ch != '\n') {
            caw.append((char) ch);
        }
        return caw.toString();
    }


    private static int getMaxInd(int[] value, int n) {
        int maxI = 0;
        for (int k = 0; k < n; k++) {
            maxI = (value[k] > value[maxI]) ? k : maxI;
        }
        return maxI;
    }

    private static int getMaxInd(TreeMap<Integer, Integer> treeMap) {
        int maxI = 0;
        if (treeMap.size() > 0) {
            maxI = treeMap.lastEntry().getValue();
        }
        return maxI;
    }


    private static int getMinInd(TreeMap<Integer, Integer> treeMap) {
        int minI = 0;
        if (treeMap.size() > 1) {
            minI = treeMap.firstEntry().getValue();
        }
        return minI;
    }

    public static int getMinInd(int[] value, int n) {
        int minI = 0;
        for (int k = 0; k < n; k++) {
            minI = (value[k] < value[minI]) ? k : minI;
        }
        return minI;
    }

}
