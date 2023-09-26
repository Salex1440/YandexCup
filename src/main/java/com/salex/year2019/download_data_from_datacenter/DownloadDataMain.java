package com.salex.year2019.download_data_from_datacenter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DownloadDataMain {
    public static void main(String[] args) throws IOException {
        String file = "src/main/java/com/salex/year2019/download_data_from_datacenter/input.txt";
        int n, q;
        Map<Integer, Set<Integer>> links = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            n = Integer.parseInt(line);
            for (int i = 0; i < n; i++) {
                line = br.readLine();
                List<Integer> list = Arrays.stream(line.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                Integer s1 = list.get(0);
                Integer s2 = list.get(1);

                if (links.containsKey(s1) && links.containsKey(s2)) {
                    Set<Integer> set1 = links.get(s1);
                    set1.add(s2);
                    links.put(s1, set1);
                    Set<Integer> set2 = links.get(s2);
                    set2.add(s1);
                    links.put(s2, set2);
                } else if (links.containsKey(s1)) {
                    Set<Integer> set1 = links.get(s1);
                    set1.add(s2);
                    links.put(s1, set1);
                    Set<Integer> set2 = new HashSet<>();
                    set2.add(s1);
                    links.put(s2, set2);
                } else if (links.containsKey(s2)) {
                    Set<Integer> set2 = links.get(s2);
                    set2.add(s1);
                    links.put(s2, set2);
                    Set<Integer> set1 = new HashSet<>();
                    set1.add(s2);
                    links.put(s1, set1);
                } else {
                    Set<Integer> set1 = new HashSet<>();
                    set1.add(s2);
                    links.put(s1, set1);
                    Set<Integer> set2 = new HashSet<>();
                    set2.add(s1);
                    links.put(s2, set2);
                }
            }
            line = br.readLine();
            q = Integer.parseInt(line);
            for (int i = 0; i < q; i++) {
                line = br.readLine();
                List<Integer> list = Arrays.stream(line.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                int x = list.get(0);
                int k = list.get(1);
                line = br.readLine();
                Set<Integer> servers = Arrays.stream(line.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toSet());
                StringBuilder sb = new StringBuilder();

            }

            System.out.println();
        }
    }
}
