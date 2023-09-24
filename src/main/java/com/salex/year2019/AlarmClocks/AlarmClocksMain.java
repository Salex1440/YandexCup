package com.salex.year2019.AlarmClocks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class AlarmClocksMain {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/main/java/com/salex/year2019/AlarmClocks/input.txt");
        Scanner scanner = new Scanner(file);
        int n = scanner.nextInt(); // количество будильников
        int x = scanner.nextInt(); // периодичность звонков
        int k = scanner.nextInt(); // количество будильников, которое нужно отключить, чтобы Алексей проснулся
        Queue<Integer> t = new PriorityQueue<>(n); // моменты времени на которые были заведены будильники
        for (int i = 0; i < n; i++) {
            t.add(scanner.nextInt());
        }

        int result = -1;
        for (int i = 0; i < k; i++) {
            result = t.poll();
            Integer nextTime = result + x;
            if (!t.contains(nextTime)) {
                t.add(nextTime);
            }
        }

        System.out.println(result);
    }

}
