package com.salex.year2019.AlarmClocks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AlarmClocksMain {

    private static long n, x, k;

    public static void main(String[] args) throws FileNotFoundException {
        Map<Long, Long> map = new HashMap<>();
        File file = new File("src/main/java/com/salex/year2019/AlarmClocks/input2.txt");
        try (Scanner scanner = new Scanner(file)) {
            n = scanner.nextInt(); // количество будильников
            x = scanner.nextInt(); // периодичность звонков
            k = scanner.nextInt(); // количество будильников, которое нужно отключить, чтобы Алексей проснулся
            long time, key, value;
            for (int i = 0; i < n; i++) {
                time = scanner.nextInt();
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
