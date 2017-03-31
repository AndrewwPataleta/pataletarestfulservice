package com.pataleta.restfullservice.Service.impl;

import com.pataleta.restfullservice.Service.parsWeb;
import com.pataleta.restfullservice.model.Auto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;

public class parsMotorland {

//    @Override
    public void getList(String searchString) throws IOException {
        System.out.println(" getlist search: "+searchString);
        int counter = 1;
        boolean check = false;
        Document doc = Jsoup.connect("https://motorland.by/auto-parts/?Filter.TextSearch="+searchString+"&pg="+counter+" ").timeout(5000).get();
        Elements mainDiv = doc.getElementsByClass("cont");
        for (Element maybeTableElement: mainDiv) {
            if(maybeTableElement.getElementsByClass("grid").size() > 0){
             Elements  maybeTableElements = maybeTableElement.getElementsByClass("grid");
                check = true;
                for (Element maybeTableElement1: maybeTableElements) {
                    Elements items = maybeTableElement1.getElementsByTag("tr");
                    for (Element qwe:items) {
                        String q = qwe.getElementsByAttributeValue("data-title","Модель").text();
                        String model = qwe.getElementsByAttributeValue("data-title","Марка").text();
                        String nameZap = qwe.getElementsByAttributeValue("data-title","Название запчасти").text();
                        String price = qwe.getElementsByClass("price").text();
                        System.out.println(q+" "+model+" "+nameZap+" "+price+" ");
                    }
                }
            }
            break;
        }
        if(!check)
            System.out.println(" НИЧЕГО НЕ НАЙДЕНО ! ");
    }
}
