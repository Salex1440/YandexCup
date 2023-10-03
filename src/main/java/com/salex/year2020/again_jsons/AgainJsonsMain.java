package com.salex.year2020.again_jsons;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AgainJsonsMain {

    public static void main(String[] args) throws IOException, ParseException {
        String file = "src/main/java/com/salex/year2020/again_jsons/input.txt";
        BufferedReader br = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(br.readLine());

        JSONArray list = new JSONArray();
        JSONParser parser = new JSONParser();
        for (int i = 0; i < n; i++) {
            String json = br.readLine();
            JSONObject jsonObject = (JSONObject) parser.parse(json);
            JSONArray shops = (JSONArray) jsonObject.get("offers");
            Iterator iterator = shops.iterator();
            while (iterator.hasNext()) {
                JSONObject next = (JSONObject) iterator.next();
                list.add(next);
            }
        }
        JSONObject jobj = new JSONObject();
        jobj.put("offers", list);
        System.out.println(jobj);
    }

}
