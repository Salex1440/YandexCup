package com.internship.year2023.TaskA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class NewHistoryMain {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/internship/year2023/TaskA/input2.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        String[] split = line.split(" ");
        int year1 = Integer.parseInt(split[0]);
        int month1 = Integer.parseInt(split[1]);
        int day1 = Integer.parseInt(split[2]);
        int hour1 = Integer.parseInt(split[3]);
        int min1 = Integer.parseInt(split[4]);
        int sec1 = Integer.parseInt(split[5]);
        line = br.readLine();
        split = line.split(" ");
        int year2 = Integer.parseInt(split[0]);
        int month2 = Integer.parseInt(split[1]);
        int day2 = Integer.parseInt(split[2]);
        int hour2 = Integer.parseInt(split[3]);
        int min2 = Integer.parseInt(split[4]);
        int sec2 = Integer.parseInt(split[5]);

        int days = getDays(year1, month1, day1, year2, month2, day2);
        int seconds = (hour2 - hour1) * 3600 + (min2 - min1) * 60 + (sec2 - sec1);
        if (seconds < 0) {
            days--;
            seconds += 3600*24;
        }

        System.out.println(days + " " + seconds);
    }

    private static int getDays(int year1, int month1, int day1, int year2, int month2, int day2) {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days = (year2 - year1) * 365;
        for (int i = 0; i < month2 - 1; i++) {
            days += daysInMonth[i];
        }
        days += day2;
        for (int i = 0; i < month1 - 1; i++) {
            days -= daysInMonth[i];
        }
        days -= day1;
        return days;
    }
}
