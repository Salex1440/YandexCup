package com.salex.year2019.download_data_from_datacenter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DownloadDataMain {
    private static Map<Integer, Set<Integer>> links = new HashMap<>();
    private static Map<Integer, Integer> nodes = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String file = "src/main/java/com/salex/year2019/download_data_from_datacenter/input.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            fillLinks(br);
            int nodeNum = 0;
            String line = br.readLine();
            int q = Integer.parseInt(line);
            for (int i = 0; i < q; i++) {
                line = br.readLine();
                List<Integer> list = Arrays.stream(line.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                int x = list.get(0);
                int k = list.get(1);

                if (!nodes.containsKey(x)) {
                    fillNodes(x, nodeNum);
                    nodeNum++;
                }
                response(br, x, k);
            }

            System.out.println();
        }
    }

    private static void fillLinks(BufferedReader br) throws IOException {
        String line = br.readLine();
        int n = Integer.parseInt(line);
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
    }

    private static void fillNodes(Integer target, int cnt) {
        if (nodes.containsKey(target)) return;
        nodes.put(target, cnt);
        Set<Integer> set = links.get(target);
        for (Integer s : set) {
            fillNodes(s, cnt);
        }
    }

    private static void response(BufferedReader br, int x, int k) throws IOException {
        String line;
        line = br.readLine();
        List<Integer> servers = Arrays.stream(line.split(" "))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for (Integer server : servers) {
            if (nodes.containsKey(server) && nodes.get(server).equals(nodes.get(x))) {
                sb.append(" ").append(server);
            } else {
                k--;
            }
        }
        sb.insert(0, k);
        System.out.println(sb);
    }
}
