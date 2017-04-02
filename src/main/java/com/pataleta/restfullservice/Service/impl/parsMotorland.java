package com.pataleta.restfullservice.Service.impl;

import com.pataleta.restfullservice.model.Sparepart;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class parsMotorland {

    private HashSet<Sparepart> listOfSparepart;
    private int pageCountInt = 1;
    private int currentPage = 1;
    private String search;

    private void initList() throws IOException{
        listOfSparepart = new HashSet<>();
        while (currentPage <= pageCountInt) {

            Document motorlandWebDoc = Jsoup.connect("http://motorland.by/auto-parts/?Filter.TextSearch=" + search + "&pg=" + currentPage + " ").timeout(5000).get();
            Elements itemsOnPage = motorlandWebDoc.select("div.cont").select("table.grid.partgrid").select("tbody").select("tr");
            for (Element sparePartElem : itemsOnPage) {
                Sparepart sparepart = new Sparepart();
                sparepart.setPrice(sparePartElem.select("td.tright.price").select("div").text().replace("р.", ""));
                sparepart.setNameSparepart(sparePartElem.getElementsByAttributeValue("data-title", "Название запчасти").text());
                sparepart.setVendorCode(sparePartElem.getElementsByAttributeValue("data-title", "Артикул").text());
                sparepart.setModelAuto(sparePartElem.getElementsByAttributeValue("data-title", "Модель").text());
                sparepart.setBrandAuto(sparePartElem.getElementsByAttributeValue("data-title", "Марка").text());
                sparepart.setCharacteristics(sparePartElem.getElementsByAttributeValue("data-title", "Характеристики").text());
                sparepart.setPhone(sparePartElem.select("td").select("div.popup-bl").select("div.viki-popup").text().trim());
                listOfSparepart.add(sparepart);
                System.out.println(currentPage + " " + sparepart);
            }
            currentPage++;
        }
    }

    public HashSet<Sparepart> getList(String searchString) throws IOException {
        search = searchString;
        System.out.println(" поиск в классе: "+search);
        Document doc = Jsoup.connect("http://motorland.by/auto-parts/?Filter.TextSearch="+search+" ").timeout(5000).get();
        try {
            String[] pageCountStr = doc.select("div.cont").select("div.badge").get(0).text().split(" / ");
            pageCountInt = Integer.valueOf(pageCountStr[1]);
        } catch (IndexOutOfBoundsException e) {
            pageCountInt = 1;
            if(doc.select("div.cont").select("table.grid.partgrid").size() == 0)
            {
                System.out.println(" Ничего не найдено ");
                return null;
            }
        }finally {
            initList();
            return listOfSparepart;
        }
    }
}
