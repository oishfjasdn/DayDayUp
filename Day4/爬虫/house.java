package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class house {
    public static void main(String[] args) throws IOException {
        int page = 5;
        for (int i = 0; i < page; i++) {
            Document doc= Jsoup.parse(new URL("https://sh.esf.fang.com/house/i3"+(i+1)+"/"), 3000);
            Elements place = doc.select(".add_shop");
            Elements price = doc.select(".price_right");
            Elements feature = doc.select(".tel_shop");
            String filePath = "C:/page"+(i+1)+".txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (int j = 0; j < place.size(); j++) {
                    System.out.println("=====================第" + (j + 1) + "套房源======================\n"+place.get(j).text() + "\n" + price.get(j).text() + "\n" + feature.get(j).text()+"\n");
                     writer.write("=====================第" + (j + 1) + "套房源======================\n"+place.get(j).text() + "\n" + price.get(j).text() + "\n" + feature.get(j).text()+"\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
