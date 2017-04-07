package com.pataleta.restfullservice.Service.impl;


import com.pataleta.restfullservice.Service.parsWeb;
import com.pataleta.restfullservice.model.SparepartEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;


@Service
public class parsExistBy implements parsWeb {

    private HashSet<SparepartEntity> listOfPartsByArticle;
    private String phone = "";

    @Override
    public HashSet<SparepartEntity> getListByTextSearch(String searchString) throws IOException {
        return new HashSet<>();
    }

    @Override
    public HashSet<SparepartEntity> getListByArticle(String article) throws IOException {
        listOfPartsByArticle = new HashSet<>();
        Document existWebDoc = Jsoup.connect("http://www.exist.by/price.aspx?pcode="+article+"").timeout(5000).get();
        Element tableExist = existWebDoc.getElementById("priceBody");
        Elements itemsInTable = tableExist.getElementsByClass("row-container");
        System.out.print(itemsInTable.size());
        for (Element singleItemInTable:itemsInTable) {
            try {
                SparepartEntity sparepartForAdd = new SparepartEntity();
                String price[] = singleItemInTable.getElementsByClass("price").text().split(" ");
                sparepartForAdd.setPrice(Float.valueOf(price[0].replace(",",".")));
                sparepartForAdd.setNameSparepart(singleItemInTable.getElementsByClass("descr").text());
                sparepartForAdd.setDaysOfDelivery(singleItemInTable.getElementsByClass("statis").text());
                sparepartForAdd.setProducer(singleItemInTable.getElementsByClass("art").text());
                sparepartForAdd.setQuantity(singleItemInTable.getElementsByClass("avail").text());
                sparepartForAdd.setPhone(phone);
                listOfPartsByArticle.add(sparepartForAdd);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return listOfPartsByArticle;
    }

    @Override
    public HashSet<SparepartEntity> getListByArticle(String article, String brand) throws IOException {
        return null;
    }

    @Override
    public HashSet<SparepartEntity> getListByCode(String code) throws IOException {
        return new HashSet<>();
    }


}
