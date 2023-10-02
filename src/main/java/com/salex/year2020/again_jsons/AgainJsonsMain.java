package com.salex.year2020.again_jsons;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class AgainJsonsMain {

    public static void main(String[] args) throws IOException, ParseException {
        String file = "src/main/java/com/salex/year2020/again_jsons/input.txt";
        BufferedReader br = new BufferedReader(new FileReader(file));
        int n = Integer.parseInt(br.readLine());

        JSONParser parser = new JSONParser();
        for (int i = 0; i < n; i++) {
            String json = br.readLine();
            JSONObject jsonObject = (JSONObject) parser.parse(json);
            JSONArray msg = (JSONArray) jsonObject.get("offers");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }

    }

    private class Shop {
        private String offerId;
        private Long marketSku;
        private Long price;

        public Shop(String offerId, Long marketSku, Long price) {
            this.offerId = offerId;
            this.marketSku = marketSku;
            this.price = price;
        }
    }

}
