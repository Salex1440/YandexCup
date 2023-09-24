package com.salex.year2019.AlarmClocks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AlarmClocksMain {

    private static int n, x, k;
    private static Map<Integer, Integer> map;

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/main/java/com/salex/year2019/AlarmClocks/input.txt");
        Scanner scanner = new Scanner(file);
        n = scanner.nextInt(); // количество будильников
        x = scanner.nextInt(); // периодичность звонков
        k = scanner.nextInt(); // количество будильников, которое нужно отключить, чтобы Алексей проснулся
        map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            int time = scanner.nextInt();
            int key = time % x;
            if (!map.containsKey(key)) {
               map.put(key, time);
           } else {
                int value = map.get(key);
                value = value < time ? value : time;
                map.put(key, value);
            }
        }

        int result = binSearch(0, k * x);

        System.out.println(result - x);
    }

    private static int binSearch(int l, int r) {

        int K = 0;
        int T = l + (r - l)/2;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            K += countK(T, e.getValue());
        }
        if (K == k) {
            return T;
        } else if (K > k) {
            return binSearch(l, T);
        } else {
            return binSearch(T, r);
        }
    }

    private static int countK(int T, int t) {
        return Math.max(0, (T - t)/x);
    }

}
