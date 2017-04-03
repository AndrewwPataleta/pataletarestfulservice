package com.pataleta.restfullservice.Service.impl;


import com.pataleta.restfullservice.model.SparepartEntity;
import com.pataleta.restfullservice.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class parsExistBy {

    private HashSet<SparepartEntity> listOfPartsByCode;
    private String phone = "";

    public HashSet<SparepartEntity> getListOfPartsByCode(String code) throws IOException {
        listOfPartsByCode = new HashSet<>();
        Document existWebDoc = Jsoup.connect("http://www.exist.by/price.aspx?pcode="+code+"").timeout(5000).get();
        Element tableExist = existWebDoc.getElementById("priceBody");
        Elements itemsInTable = tableExist.getElementsByClass("row-container");
        System.out.print(itemsInTable.size());
        for (Element singleItemInTable:itemsInTable) {
            SparepartEntity sparepartForAdd = new SparepartEntity();
//            sparepartForAdd.setPrice(singleItemInTable.getElementsByClass("price").text().replace("Br","").trim());
            sparepartForAdd.setNameSparepart(singleItemInTable.getElementsByClass("descr").text());
            sparepartForAdd.setDaysOfDelivery(singleItemInTable.getElementsByClass("statis").text());
            sparepartForAdd.setProducer(singleItemInTable.getElementsByClass("art").text());
            sparepartForAdd.setQuantity(singleItemInTable.getElementsByClass("avail").text());
            sparepartForAdd.setPhone(phone);
            listOfPartsByCode.add(sparepartForAdd);
        }
        return listOfPartsByCode;
    }
}
