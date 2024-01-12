package com.salex.year2023.TaskC;

import java.io.*;

public class LonelyMain {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/com/salex/year2023/TaskC/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(readWord(br));

        double x1 = Double.parseDouble(readWord(br));
        double y1 = Double.parseDouble(readWord(br));
        double z1 = Double.parseDouble(readWord(br));
        double D1 = Double.parseDouble(readWord(br));
        double x2 = Double.parseDouble(readWord(br));
        double y2 = Double.parseDouble(readWord(br));
        double z2 = Double.parseDouble(readWord(br));
        double D2 = Double.parseDouble(readWord(br));
        double x3 = Double.parseDouble(readWord(br));
        double y3 = Double.parseDouble(readWord(br));
        double z3 = Double.parseDouble(readWord(br));
        double D3 = Double.parseDouble(readWord(br));

        double x0 = (x1 + x2 + x3 + Math.sqrt(Math.pow(D1,2) - Math.pow(D2,2) + Math.pow(D3,2) - 2*(y1 - y2)*(y1 - y3) + 2*(z1 - z2)*(z1 - z3))) / 3;
        double y0 = (y1 + y2 + y3 + Math.sqrt(Math.pow(D1,2) - Math.pow(D2,2) + Math.pow(D3,2) - 2*(x1 - x2)*(x1 - x3) + 2*(z1 - z2)*(z1 - z3))) / 3;
        double z0 = (z1 + z2 + z3 - Math.sqrt(Math.pow(D1,2) - Math.pow(D2,2) + Math.pow(D3,2) - 2*(x1 - x2)*(x1 - x3) + 2*(y1 - y2)*(y1 - y3))) / 3;
        System.out.println(x0 + " " + y0 + " " + z0);
    }

    private static String readWord(BufferedReader br) throws IOException {
        int ch;
        CharArrayWriter caw = new CharArrayWriter();
        while ((ch = br.read()) != ' ' && ch != '\n') {
            caw.append((char) ch);
        }
        return caw.toString();
    }
}
