package com.pataleta.restfullservice.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.pataleta.restfullservice.Service.parsWeb;
import com.pataleta.restfullservice.model.SparepartEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "{idUser}/parts")
class PartsController {

    private HashSet<SparepartEntity> sparepartHashSet;
    private ArrayList<parsWeb> javaClasses;
    private Set<String> listOfBrands = new HashSet<>();


    public PartsController()  {
        javaClasses = new ArrayList<>();
        try {
            Scanner in = new Scanner(new File("D:\\softForDevelopment\\pataletaProjects\\pataletarestfulservice\\src\\main\\resources\\public\\files\\classesForPars"));
            while (in.hasNext()) {
                Object object = Class.forName("com.pataleta.restfullservice.Service.impl." + in.next() + "").newInstance();
                parsWeb web = (parsWeb) object;
                javaClasses.add(web);
            }
        } catch (IllegalAccessException | InstantiationException | FileNotFoundException | ClassNotFoundException e) {
            System.out.println(" PartsController: "+e.toString());
        }
    }


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

    private void initListSparepartsByArticleAndBrandWithoutAnalogs(String article,String brand)  {
        sparepartHashSet = new HashSet<>();
        for (parsWeb javaClass:javaClasses) {
            try {
                sparepartHashSet.addAll(javaClass.getListByArticleAndBrandWithoutAnalogs(article, brand));
            }catch (IOException e){
                System.out.println(" initListSparepartsByArticleAndBrandWithoutAnalogs "+e.toString());
            }
        }
    }

    private void initListOfBrands(String article){
        listOfBrands = new HashSet<>();
        for (parsWeb javaClass:javaClasses) {
                listOfBrands.addAll(javaClass.getListOfBrands(article));
        }
    }

    private void initListSparepartsByArticleWithBrandAnalogs(String article,String brand)  {
        sparepartHashSet = new HashSet<>();
        for (parsWeb javaClass:javaClasses) {
            try {
                sparepartHashSet.addAll(javaClass.getListByArticleAndBrandWithAnalogs(article, brand));
            }catch (IOException e){
                System.out.print("initListSparepartsByArticleWithBrandAnalogs "+e.toString());
            }
        }
    }

    private void initListSparepartsByUniqueArticleWithAnalogs(String article) {
        for (parsWeb javaClass:javaClasses) {
            try {
                sparepartHashSet.addAll(javaClass.initListOfSparepartsByUniqueArticleWithAnalogs(article));
            }catch (IOException e){
                System.out.println("initListSparepartsByArticleAnalogs "+e.toString());
            }
        }
    }

    private void initListSparepartsByUniqueArticleWithoutAnalogs(String article) {
        for (parsWeb javaClass:javaClasses) {
            try {
            sparepartHashSet.addAll(javaClass.initListOfSparepartsByUniqueArticleWithoutAnalogs(article));}
            catch (IOException e){
                System.out.println(e.toString());
            }
        }
    }

    private void initListSparepartsByText(String search)  {
        for (parsWeb javaClass:javaClasses) {
            try {
                sparepartHashSet.addAll(javaClass.getListByTextSearch(search));
            }catch (IOException e){
                System.out.println(e.toString());
            }
        }
    }


    @RequestMapping(value = "/brands/{article}", method = RequestMethod.GET )
    public Set<String> getListOfBrands(@PathVariable("article") String article, @PathVariable("idUser") String idUser){
        initListOfBrands(article);
        System.out.println(listOfBrands.size());
        return listOfBrands;
    }

    @RequestMapping(value = "/article/{search}/{brand}")
    public HashSet<SparepartEntity> getSparepartsByArticleAndBrandWithoutAnalogs(@PathVariable("search") String search, @PathVariable("idUser") String idUser,@PathVariable("brand") String brandArticle){
        try {
            initListSparepartsByArticleAndBrandWithoutAnalogs(search,brandArticle);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sparepartHashSet;
    }

    @RequestMapping(value = "/article/{search}/{brand}/analogs")
    public HashSet<SparepartEntity> getSparepartsByArticleAndBrandWithAnalogs(@PathVariable("search") String search, @PathVariable("idUser") String idUser,@PathVariable("brand") String brandArticle){
        try {
            initListSparepartsByArticleWithBrandAnalogs(search,brandArticle);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sparepartHashSet;
    }

    @RequestMapping(value = "/article/{search}", method = RequestMethod.GET)
    public HashSet<SparepartEntity> getSparepartsByArticleWithoutAnalogs(@PathVariable("search") String search, @PathVariable("idUser") String idUser) {
            sparepartHashSet = new HashSet<>();
        initListSparepartsByUniqueArticleWithoutAnalogs(search);
        return sparepartHashSet;
    }

    @RequestMapping(value = "/article/{search}/analogs")
    public HashSet<SparepartEntity> getSparepartsByArticleWithAnalogs(@PathVariable("search") String search, @PathVariable("idUser") String idUser){
          sparepartHashSet = new HashSet<>();
        initListSparepartsByUniqueArticleWithAnalogs(search);
      return sparepartHashSet;
    }

    @RequestMapping(value = "/text/{search}")
    public HashSet<SparepartEntity> getSparepartsByText(@PathVariable("search") String search, @PathVariable("idUser") String idUser) {
            initListSparepartsByText(search);
        return new HashSet<>();
    }
}