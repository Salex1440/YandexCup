package com.salex.year2019.download_data_from_datacenter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DownloadDataMain {
    private static Map<Integer, Set<Integer>> clusters = new HashMap<>();
    private static Map<Integer, Integer> nodes = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String file = "src/main/java/com/salex/year2019/download_data_from_datacenter/input2.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            fillNodes(br);
            String line = br.readLine();
            int q = Integer.parseInt(line);
            for (int i = 0; i < q; i++) {
                line = br.readLine();
                List<Integer> list = Arrays.stream(line.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                int x = list.get(0);
                int k = list.get(1);

                response(br, x, k);
            }

            System.out.println();
        }
    }

    private static void fillNodes(BufferedReader br) throws IOException {
        String line = br.readLine();
        int n = Integer.parseInt(line);
        int nodeNum = 0,node = 0;
        for (int i = 0; i < n; i++) {
            line = br.readLine();
            List<Integer> list = Arrays.stream(line.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
            Integer s1 = list.get(0);
            Integer s2 = list.get(1);

            if (nodes.containsKey(s1) && nodes.containsKey(s2)) {
                Integer node1 = nodes.get(s1);
                Integer node2 = nodes.get(s2);
                Set<Integer> set1 = clusters.get(node1);
                Set<Integer> set2 = clusters.get(node2);
                for (Integer s : set2) {
                    set1.add(s);
                    nodes.put(s, node1);
                }
                clusters.put(node1, set1);
                clusters.remove(node2);
            } else if (nodes.containsKey(s1)) {
                node = nodes.get(s1);
                nodes.put(s2, node);
                Set<Integer> set = clusters.get(node);
                set.add(s2);
                clusters.put(node, set);
            } else if (nodes.containsKey(s2)) {
                node = nodes.get(s2);
                nodes.put(s1, node);
                Set<Integer> set = clusters.get(node);
                set.add(s1);
                clusters.put(node, set);
            } else {
                nodes.put(s1, nodeNum);
                nodes.put(s2, nodeNum);
                Set<Integer> set = new HashSet<>();
                set.add(s1);
                set.add(s2);
                clusters.put(nodeNum, set);
                nodeNum++;
            }
        }
        System.out.println();
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
