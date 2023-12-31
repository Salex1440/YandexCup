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
            List<Long> list = Arrays.stream(nxk.split(" ")).map(Long::parseLong).collect(Collectors.toList());
            n = list.get(0); // количество будильников
            x = list.get(1); // периодичность звонков
            k = list.get(2); // количество будильников, которое нужно отключить, чтобы Алексей проснулся
            long time, key, value;
            int ch;
            CharArrayWriter caw = new CharArrayWriter();
            for (int i = 0; i < n; i++) {
                ch = br.read();
                while (!(ch == -1 || ch == 32 || ch == 10)) {
                    caw.append((char)ch);
                    ch = br.read();
                }
                time = Long.parseLong(caw.toString());
                caw.reset();
                key = time % x;
                if (map.containsKey(key)) {
                    value = map.get(key);
                    map.put(key, (value < time) ? value : time);
                } else {
                    map.put(key, time);
                }
            }
        }

        long K, T = 0;
        long l = 0, r = k * x, temp = 0;
        Collection<Long> values = map.values();
        while (l < r) {
            K = 0;
            T = l + (r - l) / 2;
            for (Long t : values) {
                temp = (T - t) / x;
                K += (0 >= temp) ? 0 : temp;
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
