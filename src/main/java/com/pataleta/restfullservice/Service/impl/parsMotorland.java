package com.pataleta.restfullservice.Service.impl;

import com.pataleta.restfullservice.model.SparepartEntity;
import com.pataleta.restfullservice.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;

public class parsMotorland {

    private HashSet<SparepartEntity> listOfSparepart;
    private int pageCountInt = 1;
    private int currentPage = 1;
    private String search;

    private void initList() throws IOException{
        listOfSparepart = new HashSet<>();
        while (currentPage <= pageCountInt) {

            Document motorlandWebDoc = Jsoup.connect("http://motorland.by/auto-parts/?Filter.TextSearch=" + search + "&pg=" + currentPage + " ").timeout(5000).get();
            Elements itemsOnPage = motorlandWebDoc.select("div.cont").select("table.grid.partgrid").select("tbody").select("tr");
            for (Element sparePartElem : itemsOnPage) {
                SparepartEntity sparepart = new SparepartEntity();
//                sparepart.setPrice(Float.valueOf(sparePartElem.select("td.tright.price").select("div").text().replace("р.", "")));
                sparepart.setNameSparepart(sparePartElem.getElementsByAttributeValue("data-title", "Название запчасти").text());
                sparepart.setVendorCode(sparePartElem.getElementsByAttributeValue("data-title", "Артикул").text());
                sparepart.setProducer(sparePartElem.getElementsByAttributeValue("data-title", "Марка").text());
                sparepart.setCharacteristics(sparePartElem.getElementsByAttributeValue("data-title", "Характеристики").text());
                sparepart.setPhone(sparePartElem.select("td").select("div.popup-bl").select("div.viki-popup").text().trim());
                listOfSparepart.add(sparepart);
                System.out.println(currentPage + " " + sparepart);
            }
            currentPage++;
        }
    }

    public HashSet<SparepartEntity> getList(String searchString) throws IOException {
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

    public void saveModel(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
       SparepartEntity qwe = new SparepartEntity();
       session.save(qwe);
        session.getTransaction().commit();
        session.close();
    }

}
