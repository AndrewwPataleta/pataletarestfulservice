package com.pataleta.restfullservice.controller;

import java.io.IOException;
import java.util.HashSet;

import com.pataleta.restfullservice.Service.parsWeb;
import com.pataleta.restfullservice.model.SparepartEntity;
import org.springframework.web.bind.annotation.*;
import com.pataleta.restfullservice.Service.impl.*;

@RestController
@RequestMapping(value = "{idUser}/parts")
class PartsController {

    private HashSet<SparepartEntity> sparepartHashSetByText;
    private HashSet<SparepartEntity> sparepartHashSetByArticle;
    private String idUser;
    private parsMotorland motorland;
    private parsExistBy existBy;
    private parsStopBy mlAuto;

//    private void saveResults(){
//        Session session = HibernateSessionFactory.getSessionFactory().openSession();
//        session.beginTransaction();
//        for (SparepartEntity sparepartEntity: sparepartHashSetByText) {
//            System.out.println("id: "+sparepartEntity.getIdSparepart());
//            session.save(sparepartEntity);
//        }
//        session.getTransaction().commit();
//        session.close();
//    }

//    private boolean mayDoRequest(){
//        int countRequestByUser = parsMotorland.countRequestTodayByUser(idUser);
//        return countRequestByUser < 5 ?  true :  false;
//    }
//        sparepartHashSetByText.addAll(parsExistBy.class.newInstance().getListOfPartsByCode("16400-9F910"));

    private void initListSparepartsByArticleAndBrandWithoutAnalogs(String article,String brand) throws IOException {
        mlAuto = new parsStopBy();
        sparepartHashSetByArticle.addAll(mlAuto.getListByArticle(article,brand));
    }

    private void initListSparepartsByArticleWithBrandAnalogs(String article,String brand) throws IOException {
        mlAuto = new parsStopBy();
        sparepartHashSetByArticle.addAll(mlAuto.getListByArticleWithAnalogs(article,brand));
    }

    private void initListSparepartsByArticleAnalogs(String article) throws IOException {
        mlAuto = new parsStopBy();
        sparepartHashSetByArticle.addAll(mlAuto.getListByArticleWithAnalogs(article));
    }

    private void initListSparepartsByArticle(String article) throws IOException {
        mlAuto = new parsStopBy();
        sparepartHashSetByArticle.addAll(mlAuto.getListByArticle(article));
    }

    private void initListSparepartsByText(String search) throws IllegalAccessException, InstantiationException, IOException {
//        if(motorland.getListByTextSearch(search) != null)
            sparepartHashSetByText = motorland.getListByTextSearch(search);
    }

    @RequestMapping(value = "/article/{search}/{brand}")
    public HashSet<SparepartEntity> getSparepartsByArticleAndBrandWithoutAnalogs(@PathVariable("search") String search, @PathVariable("idUser") String idUser,@PathVariable("brand") String brandArticle){
        try {
            sparepartHashSetByArticle = new HashSet<>();
            initListSparepartsByArticleAndBrandWithoutAnalogs(search,brandArticle);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sparepartHashSetByArticle;
    }

    @RequestMapping(value = "/article/{search}/analogs/{brand}")
    public HashSet<SparepartEntity> getSparepartsByArticleAndBrandWithAnalogs(@PathVariable("search") String search, @PathVariable("idUser") String idUser,@PathVariable("brand") String brandArticle){
        try {
            sparepartHashSetByArticle = new HashSet<>();
            initListSparepartsByArticleAndBrandWithoutAnalogs(search,brandArticle);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sparepartHashSetByArticle;
    }

    @RequestMapping(value = "/article/{search}", method = RequestMethod.GET)
    public HashSet<SparepartEntity> getSparepartsByArticleWithoutAnalogs(@PathVariable("search") String search, @PathVariable("idUser") String idUser){
        try {
            sparepartHashSetByArticle = new HashSet<>();
            initListSparepartsByArticle(search);

        } catch (IOException e){e.printStackTrace();}
        return sparepartHashSetByArticle;
    }

    @RequestMapping(value = "/article/{search}/analogs")
    public HashSet<SparepartEntity> getSparepartsByArticleWithAnalogs(@PathVariable("search") String search, @PathVariable("idUser") String idUser){
      try {
          sparepartHashSetByArticle = new HashSet<>();
          initListSparepartsByArticleAnalogs(search);
      } catch (IOException e){e.printStackTrace();}
      return sparepartHashSetByArticle;
    }

    @RequestMapping(value = "/text/{search}")
    public HashSet<SparepartEntity> getSparepartsByText(@PathVariable("search") String search, @PathVariable("idUser") String idUser) {
        try {
            motorland = new parsMotorland();
            sparepartHashSetByText = new HashSet<>();
            initListSparepartsByText(search);
        } catch (IllegalAccessException | IOException | InstantiationException e) {
            e.printStackTrace();
        }

//        UserRequestsEntity zxc = new UserRequestsEntity();
//        UserEntity user = new UserEntity();
//        user.setLoginIdUser("312");
//        zxc.setUserEntity(user);
//        Session session = HibernateSessionFactory.getSessionFactory().openSession();
//        session.beginTransaction();
//        session.save(zxc);
//        session.getTransaction().commit();
//        session.close();

//        if(!mayDoRequest())
//        {
//            System.out.println(" Лимит запросов превышен");
//            return null;
//        }
        return sparepartHashSetByText;
    }
}