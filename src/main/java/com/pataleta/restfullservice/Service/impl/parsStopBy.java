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
import java.util.Set;

public class parsStopBy implements parsWeb {

    private String phone = "705-40-05";
    private String basicUrl = "http://stopby.by";
    private HashSet<SparepartEntity> listOfParts = new HashSet<>();

    @Override
    public HashSet<SparepartEntity> getListByTextSearch(String searchString) throws IOException {
        return null;
    }

        private Element mainDiv(String url){
            Element mainDivElem = null;
            try {
                Document mlAutoDocument = Jsoup.connect(basicUrl + url).timeout(5000).get();
                mainDivElem = mlAutoDocument.getElementById("ajax_analogs");
            }catch (Exception e){
                e.printStackTrace();
            }
            return mainDivElem;
        }

    private Elements getItemsInTableByUrlWithoutAnalogs(String url){
        Elements items = new Elements();
        try {
            Element mainTable = mainDiv(url);
            items = mainTable.select("table.details-list.filterResultTable.xsmalls").select("tbody").first().select("tr");
        }catch (Exception e){
            System.out.println(" getItemsInTableWithoutAnalogs свалился ");
        }
        return items;
    }

    private Elements getItemsInTableByUrlWithAnalogs(String url){
        Elements items = new Elements();
        try {
            Element mainTable = mainDiv(url);
            items = mainTable.select("table.details-list.filterResultTable.xsmalls").select("tbody").select("tr");
        }catch (Exception e){
            System.out.println(" getItemsInTableWithAnalogs свалился ");
        }
        return items;
    }

    private void initListByElements(Elements items) throws IOException {
        try {
            for (Element singleItem : items) {
                SparepartEntity item = new SparepartEntity();
                try {
                    item.setPrice(Float.valueOf(singleItem.getElementsByAttributeValue("itemprop", "price").text()));
                    if(singleItem.select("a.fancybox-small.droppeda").text().trim().equals(""))
                        continue;
                    item.setProducer(singleItem.select("a.fancybox-small.droppeda").text());
                    item.setNameSparepart(singleItem.select("td").select("a").text());
                    item.setPrice(Float.valueOf(singleItem.getElementsByAttributeValue("itemprop", "price")
                            .text().replace(",",".")));
                    if(singleItem.select("td.g-brand.cell").text().trim().equals(""))
                        continue;
                    item.setProducer(singleItem.select("td.g-brand.cell").text());
                    item.setNameSparepart(singleItem.select("td.g-descr.cell").select("a").text().replace("Купить",""));
                    item.setDaysOfDelivery(singleItem.select("td.g-delivery.smallprice.cell.t-center").text());
                    item.setQuantity(singleItem.select("td.g-box.cell").text());
                    item.setVendorCode(singleItem.select("td.g-article.cell").text());
                    item.setPhone(phone);
                    item.setResources("stopby.by");
                    listOfParts.add(item);

                } catch (Exception e) {
                    System.out.println(" parsstop initList свалился ");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Elements getEveryoneBrandByArticle(String article)  {
        Elements itemsInTable = new Elements();
        try {
            Document mlAutoDoc = Jsoup.connect("http://stopby.by/search/number/?article=" + article + "").timeout(5000).get();
            Element mainTable = mlAutoDoc.getElementById("ajax_analogs");
            String producer = mainTable.select("table").first().select("tbody").select("tr").select("td").select("h1.uppercase").text();
            itemsInTable = mainTable.select("table").select("tbody").select("tr.cursor");
        }catch (Exception e){
            System.out.println(" getEveryone crash ");
        }
        return itemsInTable;
    }

    @Override
    public HashSet<SparepartEntity> getListByArticle(String article) throws IOException {
        try {
            Elements itemsInTable = getEveryoneBrandByArticle(article);
            for (Element singleItem : itemsInTable) {
                Element forHref = singleItem.select("td").select("strong").select("a").first();
                String href = forHref.attr("href");
                Elements itemsWithoutAnalogs = getItemsInTableByUrlWithoutAnalogs(href);
                initListByElements(itemsWithoutAnalogs);
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
    public HashSet<SparepartEntity> getListByArticleWithAnalogs(String article) throws IOException {
        try {
            Elements itemsInTable = getEveryoneBrandByArticle(article);
            for (Element singleItem : itemsInTable) {
                Element forHref = singleItem.select("td").select("strong").select("a").first();
                String href = forHref.attr("href");
                Elements itemsWithoutAnalogs = getItemsInTableByUrlWithAnalogs(href);
                initListByElements(itemsWithoutAnalogs);
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
    public HashSet<SparepartEntity> getListByArticle(String article, String brand) throws IOException {
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
        return new HashSet<>();
    }

    @Override
    public HashSet<SparepartEntity> getListByArticleWithAnalogs(String article, String brand) throws IOException {
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

    @Override
    public HashSet<SparepartEntity> getListByCode(String code) throws IOException {
        return null;
    }
}