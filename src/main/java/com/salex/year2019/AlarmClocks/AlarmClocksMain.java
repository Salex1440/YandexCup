package com.salex.year2019.AlarmClocks;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AlarmClocksMain {

    private static long n, x, k;

    public static void main(String[] args) throws IOException {
        Map<Long, Long> map = new HashMap<>();
        File file = new File("src/main/java/com/salex/year2019/AlarmClocks/input.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String nxk = br.readLine();
            List<Long> list = Arrays.stream(nxk.split(" ")).map(Long::valueOf).collect(Collectors.toList());
            n = list.get(0); // количество будильников
            x = list.get(1); // периодичность звонков
            k = list.get(2); // количество будильников, которое нужно отключить, чтобы Алексей проснулся
            long time, key, value;
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                int ch;
                while ((ch = br.read()) != -1) {
                    if (ch == 32 || ch == 10) break;
                    sb.append((char)ch);
                }
                time = Long.valueOf(sb.toString());
                key = time % x;
                if (!map.containsKey(key)) {
                    map.put(key, time);
                } else {
                    value = map.get(key);
                    value = Math.min(value, time);
                    map.put(key, value);
                }
            }
        }

        long K, T = 0;
        long l = 0, r = k * x;
        while (l < r) {
            K = 0;
            T = l + (r - l) / 2;
            for (Long t : map.values()) {
                K += Math.max(0, (T - t) / x);
            }
            if (K == k) {
                break;
            } else if (K > k) {
                r = T;
            } else {
                l = T;
            }
        }

        System.out.println(T - x);
    }

}
