package com.salex.year2020.negative_numbers_sorting;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NegativeNumbersSortingMain {

    public static void main(String[] args) throws IOException {
        String file = "src/main/java/com/salex/year2020/negative_numbers_sorting/input.txt";
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String host = br.readLine();
        String port = br.readLine();
        String a = br.readLine();
        String b = br.readLine();
        sb.append(host)
            .append(":")
            .append(port)
            .append("?a=")
            .append(a)
            .append("&b=")
            .append(b);
        URL url = new URL(sb.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

//        String responseFile = "src/main/java/com/salex/year2020/negative_numbers_sorting/response.json";
        JSONParser parser = new JSONParser();
        List<Long> list = new ArrayList<>();
        try (Reader reader = new InputStreamReader(connection.getInputStream())) { // new FileInputStream(responseFile)
            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            for (int i = 0; i < jsonArray.size(); i++) {
                Long val = (Long) jsonArray.get(i);
                if (val < 0) {
                    list.add(val);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Collections.sort(list);

        for (Long e : list) {
            System.out.println(e);
        }
    }

}
