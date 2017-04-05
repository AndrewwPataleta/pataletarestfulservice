package com.pataleta.restfullservice.Service.impl;


import com.pataleta.restfullservice.Service.parsWeb;
import com.pataleta.restfullservice.model.SparepartEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class parsMlAuto implements parsWeb {

    @Override
    public HashSet<SparepartEntity> getListByTextSearch(String searchString) throws IOException {
        return null;
    }

    @Override
    public HashSet<SparepartEntity> getListByArticle(String article) throws IOException {
        Document existWebDoc = Jsoup.connect("http://ml-auto.by/search/preload/?article="+article+"").timeout(5000).get();
        Element mainDiv = existWebDoc.getElementById("ajax_analogs");
        System.out.println(mainDiv);
        return new HashSet<>();
    }

    @Override
    public HashSet<SparepartEntity> getListByCode(String code) throws IOException {
        return null;
    }
}
