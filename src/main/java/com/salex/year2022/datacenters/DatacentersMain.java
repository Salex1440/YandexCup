package com.salex.year2022.datacenters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
        Arrays.fill(A, m);
        for (int i = 0; i < q; i++) {
            line = br.readLine();
            split = line.split(" ");
            System.out.println(line);
        }

    }
}
