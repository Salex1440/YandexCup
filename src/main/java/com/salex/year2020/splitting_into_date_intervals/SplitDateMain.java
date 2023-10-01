package com.salex.year2020.splitting_into_date_intervals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SplitDateMain {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/salex/year2020/" +
            "splitting_into_date_intervals/input3.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String interval = br.readLine();
        String line = br.readLine();
        String[] vals = line.split(" ");
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate start = LocalDate.from(formatter.parse(vals[0]));
        LocalDate end = LocalDate.from(formatter.parse(vals[1]));

        List<String> intervals = new ArrayList<>();
        switch (interval) {
            case "WEEK":
                intervals = getWeekIntervals(start, end);
                break;
            case "MONTH":
                intervals = getMonthIntervals(start, end);
                break;
            case "QUARTER":
                intervals = getQuarterIntervals(start, end);
                break;
            case "YEAR":
                intervals = getYearIntervals(start, end);
                break;
            case "FRIDAY_THE_13TH":
                intervals = get13FridayIntervals(start, end);
                break;
        }

        System.out.println(intervals.size());
        for (String i : intervals) {
            System.out.println(i);
        }

    }

    static List<String> getWeekIntervals(LocalDate start, LocalDate end) {
        List<String> result = new ArrayList<>();
        if (start.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            result.add(start + " " + start);
            start = start.plusDays(1);
        }
        LocalDate temp;
        while (start.isBefore(end) || start.equals(end)) {
            temp = start.plusDays(6);
            if (temp.isAfter(end)) {
                result.add(start + " " + end);
                start = end.plusDays(1);
            } else {
                result.add(start + " " + temp);
                start = temp.plusDays(1);
            }
        }
        return result;
    }

    static List<String> getMonthIntervals(LocalDate start, LocalDate end) {
        List<String> result = new ArrayList<>();
        LocalDate temp;
        while (start.isBefore(end) || start.equals(end)) {
            temp = YearMonth.of(start.getYear(), start.getMonth()).atEndOfMonth();
            if (temp.isAfter(end)) {
                result.add(start + " " + end);
                start = end.plusDays(1);
            } else {
                result.add(start + " " + temp);
                start = temp.plusDays(1);
            }
        }
        return result;
    }

    static List<String> getQuarterIntervals(LocalDate start, LocalDate end) {
        List<String> result = new ArrayList<>();
        LocalDate temp = start;
        while (start.isBefore(end) || start.equals(end)) {
            if (start.getMonthValue() >= 1 && start.getMonthValue() <= 3) {
                temp = YearMonth.of(start.getYear(), 3).atEndOfMonth();
            } else if (start.getMonthValue() >= 4 && start.getMonthValue() <= 6) {
                temp = YearMonth.of(start.getYear(), 6).atEndOfMonth();
            } else if (start.getMonthValue() >= 7 && start.getMonthValue() <= 9) {
                temp = YearMonth.of(start.getYear(), 9).atEndOfMonth();
            } else if (start.getMonthValue() >= 10 && start.getMonthValue() <= 12) {
                temp = YearMonth.of(start.getYear(), 12).atEndOfMonth();
            }
            if (temp.isAfter(end)) {
                result.add(start + " " + end);
                start = end.plusDays(1);
            } else {
                result.add(start + " " + temp);
                start = temp.plusDays(1);
            }
        }
        return result;
    }

    static List<String> getYearIntervals(LocalDate start, LocalDate end) {
        List<String> result = new ArrayList<>();
        LocalDate temp;
        while (start.isBefore(end) || start.equals(end)) {
            temp = YearMonth.of(start.getYear(), 12).atEndOfMonth();
            if (temp.isAfter(end)) {
                result.add(start + " " + end);
                start = end.plusDays(1);
            } else {
                result.add(start + " " + temp);
                start = temp.plusDays(1);
            }
        }
        return result;
    }

    static List<String> get13FridayIntervals(LocalDate start, LocalDate end) {
        List<String> result = new ArrayList<>();
        LocalDate temp;
        for (int year = start.getYear(), month = start.getMonthValue(); year < end.getYear() || month <= end.getMonthValue(); month++) {
            if (month > 12) {
                month = 1;
                year++;
            }
            if (LocalDate.of(year, month, 12).isBefore(start)) {
                month++;
            }
            temp = LocalDate.of(year, month, 12);
            if (temp.getDayOfWeek().equals(DayOfWeek.THURSDAY)) {
                result.add(start + " " + temp);
                start = temp.plusDays(1);
            }
        }
        result.add(start + " " + end);
        return result;
    }

}
