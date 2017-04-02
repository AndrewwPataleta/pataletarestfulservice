package com.pataleta.restfullservice.Service.impl;


import com.pataleta.restfullservice.model.Sparepart;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class parsExistBy {

    public HashSet<Sparepart> getListOfParts() throws IOException {
        Document existWebDoc = Jsoup.connect("http://www.exist.by/price.aspx?pcode=16400-9F910").timeout(5000).get();
        Element tableExist = existWebDoc.getElementById("priceBody");
        Elements itemsInTable = tableExist.getElementsByClass("row-container");
        String  desr = itemsInTable.get(1).getElementsByClass("price").get(0).text();
        System.out.println(desr);
        return null;
    }

}
