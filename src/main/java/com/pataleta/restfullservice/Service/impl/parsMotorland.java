package com.pataleta.restfullservice.Service.impl;

import com.pataleta.restfullservice.Service.parsWeb;
import com.pataleta.restfullservice.model.SparepartEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;

public class parsMotorland implements parsWeb {

    private Properties property = new Properties();
    private HashSet<SparepartEntity> listOfSparepart = new HashSet<>();
    private int pageCountInt = 1;
    private int currentPage = 1;
    private String search;

    private void initClass() throws IOException{
//        listOfSparepart = new HashSet<>();
//        property.load(new FileInputStream("properties/configuration.properties"));
//        System.out.println(" size: " +property.size());
    }


    private void parsingTable(Document motorlandWebDoc){
        Elements itemsOnPage = motorlandWebDoc.select("div.cont").select("table.grid.partgrid").select("tbody").select("tr");
        for (Element sparePartElem : itemsOnPage) {
            try {
                SparepartEntity sparepart = new SparepartEntity();
                String [] priceStr = sparePartElem.select("td.tright.price").select("div").text().replace("р.", "").split(" ");
                sparepart.setPrice(Float.valueOf(priceStr[0].replace(",",".")));
                sparepart.setNameSparepart(sparePartElem.getElementsByAttributeValue("data-title", "Название запчасти").text());
                sparepart.setVendorCode(sparePartElem.getElementsByAttributeValue("data-title", "Артикул").text());
                sparepart.setProducer(sparePartElem.getElementsByAttributeValue("data-title", "Марка").text());
                sparepart.setCharacteristics(sparePartElem.getElementsByAttributeValue("data-title", "Характеристики").text());
                sparepart.setPhone(sparePartElem.select("td").select("div.popup-bl").select("div.viki-popup").text().trim());
                listOfSparepart.add(sparepart);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void initListByTextSearch() throws IOException{
        listOfSparepart = new HashSet<>();
        while (currentPage <= pageCountInt) {
            Document motorlandWebDoc = Jsoup.connect("http://motorland.by/auto-parts/?Filter.TextSearch=" + search + "&pg=" + currentPage + " ")
                    .timeout(5000).get();
            parsingTable(motorlandWebDoc);
            currentPage++;
        }
    }

    @Override
    public HashSet<SparepartEntity> getListByTextSearch(String searchString) throws IOException {
        search = searchString;
        Document doc = Jsoup.connect("http://motorland.by/auto-parts/?Filter.TextSearch="+search+" ").timeout(5000).get();
        try {
            String[] pageCountStr = doc.select("div.cont").select("div.badge").get(0).text().split(" / ");
            pageCountInt = Integer.valueOf(pageCountStr[1]);
        } catch (IndexOutOfBoundsException e) {
            pageCountInt = 1;
            if(doc.select("div.cont").select("table.grid.partgrid").size() == 0)
            {
                System.out.println(" Ничего не найдено ");
                return listOfSparepart;
            }
        }
            initListByTextSearch();
            return listOfSparepart;
    }

    @Override
    public HashSet<SparepartEntity> getListByArticle(String article) throws IOException {
        Document motorlandWebDoc = Jsoup.connect("https://motorland.by/auto-parts/?Filter.Article="+article+" ")
                .timeout(5000).get();
        Elements itemsInTable = motorlandWebDoc.select("div.cont").select("table.grid.partgrid").select("tbody").select("tr");
        if(itemsInTable.size() == 0)
            return listOfSparepart;
        parsingTable(motorlandWebDoc);
        return listOfSparepart;
    }


    @Override
    public HashSet<SparepartEntity> getListByCode(String code) throws IOException {

        return new HashSet<>();
    }

//   public static int countRequestTodayByUser(String idUser){
//       Session session = HibernateSessionFactory.getSessionFactory().openSession();
//       session.beginTransaction();
//       Object result = session.getSession().createCriteria(UserRequestsEntity.class)
//               .add(Restrictions.eq("loginIdUser",idUser))
//               .add(Restrictions.eq("dateRequest",new Date()))
//                    .setProjection(Projections.rowCount()).uniqueResult();
//       System.out.println(" Кол-во: "+Integer.valueOf(result.toString()));
//       return Integer.valueOf(result.toString());
//   }
//    public void saveModel(){
//        Session session = HibernateSessionFactory.getSessionFactory().openSession();
//        session.beginTransaction();
//       SparepartEntity qwe = new SparepartEntity();
//       session.save(qwe);
//        session.getTransaction().commit();
//        session.close();
//    }

}
