//package com.pataleta.restfullservice.Service.impl;
//
//
//import com.pataleta.restfullservice.Service.parsWeb;
//import com.pataleta.restfullservice.model.SparepartEntity;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.Objects;
//import java.util.Set;
//
//
//@Service
//public class parsExistBy implements parsWeb {
//
//    private HashSet<SparepartEntity> listOfPartsByArticle;
//    private String phone = "";
//    private Set<String> listOfBrands = new HashSet<>();
//
//    @Override
//    public HashSet<SparepartEntity> getListByTextSearch(String searchString) throws IOException {
//        return new HashSet<>();
//    }
//
//    @Override
//    public HashSet<SparepartEntity> initListOfSparepartsByUniqueArticleWithoutAnalogs(String article) throws IOException {
//        return null;
//    }
//
//    @Override
//    public HashSet<SparepartEntity> getListByArticleAndBrandWithoutAnalogs(String article, String brand) throws IOException {
//        return null;
//    }
//
//    @Override
//    public HashSet<SparepartEntity> initListOfSparepartsByUniqueArticleWithAnalogs(String article) throws IOException {
//        return null;
//    }
//
//    @Override
//    public HashSet<SparepartEntity> getListByArticleAndBrandWithAnalogs(String article, String brand) throws IOException {
//        return null;
//    }
//
//    @Override
//    public HashSet<SparepartEntity> getListByArticle(String article) throws IOException {
//        listOfPartsByArticle = new HashSet<>();
//        Document existWebDoc = Jsoup.connect("http://www.exist.by/price.aspx?pcode="+article+"").timeout(5000).get();
//        Element tableExist = existWebDoc.getElementById("priceBody");
//        Elements itemsInTable = tableExist.getElementsByClass("row-container");
//        System.out.print(itemsInTable.size());
//        for (Element singleItemInTable:itemsInTable) {
//            try {
//                SparepartEntity sparepartForAdd = new SparepartEntity();
//                String price[] = singleItemInTable.getElementsByClass("price").text().split(" ");
//                sparepartForAdd.setPrice(Float.valueOf(price[0].replace(",",".")));
//                sparepartForAdd.setNameSparepart(singleItemInTable.getElementsByClass("descr").text());
//                sparepartForAdd.setDaysOfDelivery(singleItemInTable.getElementsByClass("statis").text());
//                sparepartForAdd.setProducer(singleItemInTable.getElementsByClass("art").text());
//                sparepartForAdd.setQuantity(singleItemInTable.getElementsByClass("avail").text());
//                sparepartForAdd.setPhone(phone);
//                listOfPartsByArticle.add(sparepartForAdd);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        return listOfPartsByArticle;
//    }
//
//
//    private void initListOfBrands(Elements brandsElem){
//        System.out.println(" tyt ");
//        for (Element brand: brandsElem) {
//            if(brand.select("li").select("a").select("span").select("b").text() != null && !Objects.equals(brand.select("li").select("a").select("span").select("b").text(), ""))
//                listOfBrands.add(brand.select("li").select("a").select("span").select("b").text());
//        }
//    }
//
//    @Override
//    public Set<String> getListOfBrands(String article) {
//        Elements brandsElem = getEveryoneBrandByArticle(article);
//      initListOfBrands(brandsElem);
//      return listOfBrands;
//    }
//
//
//    private Elements getEveryoneBrandByArticle(String article)  {
//        Elements itemsInTable = new Elements();
//        try {
//            Document mlAutoDoc = Jsoup.connect("http://www.exist.by/price.aspx?pcode="+article+"").timeout(5000).get();
//            Element mainTable = mlAutoDoc.getElementById("priceBody");
//            itemsInTable = mainTable.getElementsByClass("catalogs").select("li");
//        }catch (NullPointerException | IOException e){
//            System.out.println(e.toString());
//        }
//        return itemsInTable;
//    }
//
//
//}
