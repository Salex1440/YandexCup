package com.salex.year2019.AlarmClocks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AlarmClocksMain {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/main/java/com/salex/year2019/AlarmClocks/input.txt");
        Scanner scanner = new Scanner(file);
        int n = scanner.nextInt(); // количество будильников
        int x = scanner.nextInt(); // периодичность звонков
        int k = scanner.nextInt(); // количество будильников, которое нужно отключить, чтобы Алексей проснулся
        Queue<Integer> t = new PriorityQueue<>(); // моменты времени на которые были заведены будильники
        Map<Integer, Integer> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int time = scanner.nextInt();
            int key = time % x;
            if (!groups.containsKey(key)) {
               groups.put(key, time);
           } else {
                Integer value = groups.get(key);
                value = value < time ? value : time;
                groups.put(key, value);
            }
        }
        for (Map.Entry<Integer, Integer> entry : groups.entrySet()) {
            t.add(entry.getValue());
        }

        int result = 0;
        for (int i = 0; i < k; i++) {
            result = t.poll();
            Integer nextTime = result + x;
            t.add(nextTime);
        }

        System.out.println(result);
    }

}
