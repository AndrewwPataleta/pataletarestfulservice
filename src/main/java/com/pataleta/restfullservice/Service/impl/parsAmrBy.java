package com.pataleta.restfullservice.Service.impl;

import com.pataleta.restfullservice.Service.parsWeb;
import com.pataleta.restfullservice.model.SparepartEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class parsAmrBy implements parsWeb {

        private String phone = "+375(33)617-61-99";
        private String basicUrl = "http://amr.by";
        private HashSet<SparepartEntity> listOfParts = new HashSet<>();

        @Override
        public HashSet<SparepartEntity> getListByTextSearch(String searchString) throws IOException {
            return null;
        }

        private Element mainDiv(String url){
            Element mainDivElem = null;
            try {
                Document mlAutoDocument = Jsoup.connect(basicUrl + url).timeout(5000).get();
                mainDivElem = mlAutoDocument.getElementById("ajax_load_search_data");
            }catch (Exception e){
                e.printStackTrace();
            }
            return mainDivElem;
        }

        private Elements getItemsInTableByUrlWithoutAnalogs(String url){
            if(url.equals(null)|| Objects.equals(url.trim(), ""))
                return new Elements();
            Elements items = new Elements();
            try {
                Element mainTable = mainDiv(url);
                items = mainTable.select("table.xsmalls").first().select("tbody").select("tr");
            }catch (Exception e){
                System.out.println(e.toString());
            }
            return items;
        }

        private Elements getItemsInTableByUrlWithAnalogs(String url){
            Elements items = new Elements();
            try {
                Element mainTable = mainDiv(url);
                items = mainTable.select("table.details-list.filterResultTable.xsmalls").select("tbody").select("tr");
            }catch (Exception e){
                System.out.println(e.toString());
            }
            return items;
        }

        private void initListByElements(Elements items) throws IOException {
            try {
                for (Element singleItem : items) {
                    SparepartEntity item = new SparepartEntity();
                    try {
                        item.setPrice(Float.valueOf(singleItem.select("td.th-td-result-price.box-price-view.cell.td-color2").text().replace("BYN","")));
                        if(singleItem.select("a.fancy_inline.droppeda").text().trim().equals(""))
                            continue;
                        if(singleItem.select("td").select("p").select("span.descr-hide-overflow").text().trim().equals(""))
                            continue;
                        item.setProducer(singleItem.select("td").select("span").select("b").select("a.fancy_inline.droppeda").text());
                        item.setNameSparepart(singleItem.select("td").select("p").select("span.descr-hide-overflow").text().trim());
                        item.setDaysOfDelivery(singleItem.select("td.th-td-result-delivery.smallprice.cell.td-color2").text());
                        item.setQuantity(singleItem.select("td.th-td-result-box.cell.td-color2").text());
                        item.setVendorCode(singleItem.select("td.th-td-result-article.cell.td-color2").text());
                        item.setPhone(phone);
                        item.setResources("amr.by");
                        listOfParts.add(item);
                    } catch (Exception e) {
                        System.out.println(e.toString() );
                    }

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        private Elements getEveryoneBrandByArticle(String article)  {
            Elements itemsInTable = new Elements();
            try {
                Document mlAutoDoc = Jsoup.connect("http://amr.by/search/artlookup/?ajax=true&sort=article&time=false&article="+article+"").timeout(5000).get();
                Element mainTable = mlAutoDoc.getElementById("ajax_analogs");
                String producer = mainTable.select("table").first().select("tbody").select("tr").select("td").select("h1.uppercase").text();
                itemsInTable = mainTable.select("table").select("tbody").select("tr.cursor");
            }catch (NullPointerException | IOException e){
                System.out.println(e.toString());
            }
            return itemsInTable;
        }


        public HashSet<SparepartEntity> initListOfSparepartsByUniqueArticleWithoutAnalogs(String article){
            Elements itemsInTable;
            try {
                Document listElementsByArticle = Jsoup.connect("http://amr.by/search/number/?article=F2AZ4635C&brand=Ford&ajax=true").timeout(5000).get();
                Element mainDiv = listElementsByArticle.getElementById("ajax_analogs");
                itemsInTable = mainDiv.select("table.details-list.filterResultTable.xsmalls").first().select("tbody").select("tr");
                initListByElements(itemsInTable);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return listOfParts;
        }

    @Override
    public HashSet<SparepartEntity> initListOfSparepartsByUniqueArticleWithAnalogs(String article) throws IOException {
        Elements itemsInTable;
        try {
            Document listElementsByArticle = Jsoup.connect("http://amr.by/search/number/?article="+article+"&brand=&ajax=true").timeout(4000).get();
            System.out.print(listElementsByArticle);
            Element mainDiv = listElementsByArticle.getElementById("ajax_analogs");
            itemsInTable = mainDiv.select("table.details-list.filterResultTable.xsmalls").select("tbody").select("tr");
            System.out.println(mainDiv  );
            initListByElements(itemsInTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfParts;
    }

        @Override
        public HashSet<SparepartEntity> getListByArticleAndBrandWithoutAnalogs(String article, String brand) throws IOException {
            try {
                Elements brands = getEveryoneBrandByArticle(article);
                for (Element singleItem : brands) {
                    if(!singleItem.select("td").select("strong").select("a").first().text().equals(brand))
                        continue;
                    Element forHref = singleItem.select("td").select("strong").select("a").first();
                    String href = forHref.attr("href");
                    Elements itemsWithOutAnalogs = getItemsInTableByUrlWithoutAnalogs(href);
                    initListByElements(itemsWithOutAnalogs);
                }
            }catch (SocketTimeoutException e){
                System.out.println(" Время ожидания истекло ");
            }
            catch (NullPointerException e){
                System.out.println(" Ничего не найдено ");
            }
            for (SparepartEntity item: listOfParts) {
                System.out.println(item);
            }
            return listOfParts;
        }

        @Override
        public Set<String> getListOfBrands(String article) {
            Set<String> brands;
                Elements brandsElem = getEveryoneBrandByArticle(article);
                brands = new HashSet<>();
            for (Element brand:brandsElem) {
                if(brand.select("td").select("strong").select("a").text() != null &&
                        !Objects.equals(brand.select("td").select("strong").select("a").text(), ""))
                brands.add(brand.select("td").select("strong").select("a").text());
            }
            return brands;
        }



    @Override
        public HashSet<SparepartEntity> getListByArticleAndBrandWithAnalogs(String article, String brand) throws IOException {
            try {
                Elements brands = getEveryoneBrandByArticle(article);
                for (Element singleItem : brands) {
                    if(!singleItem.select("td").select("strong").select("a").first().text().equals(brand))
                        continue;
                    Element forHref = singleItem.select("td").select("strong").select("a").first();
                    String href = forHref.attr("href");
                    Elements itemsWithAnalogs = getItemsInTableByUrlWithAnalogs(href);
                    initListByElements(itemsWithAnalogs);
                }
            }catch (SocketTimeoutException e){
                System.out.println(" Время ожидания истекло ");
            }
            catch (NullPointerException e){
                System.out.println(" Ничего не найдено ");
            }
            for (SparepartEntity item: listOfParts) {
                System.out.println(item);
            }
            return listOfParts;
        }
    }
