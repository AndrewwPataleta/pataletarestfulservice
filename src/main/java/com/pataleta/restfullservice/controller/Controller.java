package com.pataleta.restfullservice.controller;

import java.io.IOException;
import java.util.HashSet;

import com.pataleta.restfullservice.model.SparepartEntity;
import com.pataleta.restfullservice.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.*;
import com.pataleta.restfullservice.Service.impl.*;

@RestController
class ControllerByDesc {

    HashSet<SparepartEntity> sparepartHashSet = null;

    private String idUser;

    private void saveResults(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        for (SparepartEntity sparepartEntity:sparepartHashSet) {
            System.out.println("id: "+sparepartEntity.getIdSparepart());
            session.save(sparepartEntity);
        }
        session.getTransaction().commit();
        session.close();
    }

//    private boolean mayDoRequest(){
//        int countRequestByUser = parsMotorland.countRequestTodayByUser(idUser);
//        return countRequestByUser < 5 ?  true :  false;
//    }

    private void initMainListSpareparts(String search) throws IllegalAccessException, InstantiationException, IOException {
        parsMotorland motorland = new parsMotorland();
        sparepartHashSet = motorland.getList(search);
        sparepartHashSet = parsExistBy.class.newInstance().getListOfPartsByCode("16400-9F910");
    }

    @RequestMapping(value = "{idUser}/parts/{search}/{test}")   
    public HashSet<SparepartEntity> getSparepartsByDesc(@PathVariable("search") String search, @PathVariable("test") String test,@PathVariable("idUser") String idUser) {
        System.out.println(" Номер: "+idUser);
        this.idUser = idUser;



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

        System.out.println(" окей ");

        return sparepartHashSet;
    }
}