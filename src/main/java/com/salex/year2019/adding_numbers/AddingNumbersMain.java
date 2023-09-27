package com.salex.year2019.adding_numbers;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.*;

public class AddingNumbersMain {

    public static void main(String[] args) throws IOException {
        String file = "src/main/java/com/salex/year2019/adding_numbers/input.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
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
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

//            String responseFile = "src/main/java/com/salex/year2019/adding_numbers/response.json";
            JSONParser parser = new JSONParser();
            Long sum = 0L;
            try (Reader reader = new InputStreamReader(connection.getInputStream())) {
                JSONArray jsonArray = (JSONArray) parser.parse(reader);
                for (int i = 0; i < jsonArray.size(); i++) {
                    sum += (Long)jsonArray.get(i);
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            System.out.println(sum);
        }

    }
}
