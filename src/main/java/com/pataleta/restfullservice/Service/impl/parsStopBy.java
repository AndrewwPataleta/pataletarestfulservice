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

public class parsStopBy implements parsWeb {

    private String basicUrl = "http://stopby.by";
    private HashSet<SparepartEntity> listOfParts = new HashSet<>();

    @Override
    public HashSet<SparepartEntity> getListByTextSearch(String searchString) throws IOException {
        return null;
    }

    private void initListByHref(String urlForSearch) throws IOException {
        try {
            Document mlAutoDocument = Jsoup.connect(basicUrl + urlForSearch).timeout(5000).get();
            Element mainTable = mlAutoDocument.getElementById("ajax_analogs");
            Elements itemsInTable = mainTable.select("table.details-list.filterResultTable.xsmalls").select("tbody").select("tr");
            for (Element singleItem : itemsInTable) {
                SparepartEntity item = new SparepartEntity();
                try {
                    item.setPrice(Float.valueOf(singleItem.getElementsByAttributeValue("itemprop", "price").text()));
                    if(singleItem.select("a.fancybox-small.droppeda").text().trim().equals(""))
                         continue;
                    item.setProducer(singleItem.select("a.fancybox-small.droppeda").text());
                    item.setNameSparepart(singleItem.select("td").select("a").text());
                    item.setDaysOfDelivery(singleItem.select("td.g-delivery.smallprice.cell.t-center").text());
                    item.setQuantity(singleItem.select("td.g-box.cell").text());
                    item.setVendorCode(singleItem.select("td.g-article.cell").text());
                    listOfParts.add(item);

                } catch (Exception e) {
//                    System.out.println(" без цены ");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public HashSet<SparepartEntity> getListByArticle(String article) throws IOException {
            try {
                Document mlAutoDoc = Jsoup.connect("http://stopby.by/search/number/?article=" + article + "").timeout(5000).get();
                Element mainTable = mlAutoDoc.getElementById("ajax_analogs");
                String producer = mainTable.select("table").first().select("tbody").select("tr").select("td").select("h1.uppercase").text();
                Elements itemsInTable = mainTable.select("table").select("tbody").select("tr.cursor");
                for (Element singleItem : itemsInTable) {
                    Element forHref = singleItem.select("td").select("strong").select("a").first();
                    String href = forHref.attr("href");
                    initListByHref(href);
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
    public HashSet<SparepartEntity> getListByCode(String code) throws IOException {
        return null;
    }
}
