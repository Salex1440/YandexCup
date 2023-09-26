package com.salex.year2019.bad_commit_searching;

import java.util.Scanner;

public class BadCommitSearchMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int l = 0, m;
        while (l < r) {
            m = (l + r) / 2;
            System.out.println(m + " \n");
            System.out.flush();
            int resp = sc.nextInt();
            if (resp == 0) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        System.out.println("! " + l);
    }
}
