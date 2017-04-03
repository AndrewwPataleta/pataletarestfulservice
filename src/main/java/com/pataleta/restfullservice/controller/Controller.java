package com.pataleta.restfullservice.controller;

import java.io.IOException;
import java.util.HashSet;

import com.pataleta.restfullservice.model.RequestsEntity;
import com.pataleta.restfullservice.model.SparepartEntity;
import com.pataleta.restfullservice.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.*;
import com.pataleta.restfullservice.Service.impl.*;

@RestController
class ControllerByDesc {

//    @RequestParam(value="name", required=false, defaultValue="World") String name

    @RequestMapping(value = "/parts/{search}/{test}")
    public HashSet<SparepartEntity> getSparepartsByDesc(@PathVariable("search") String search, @PathVariable("test") String test) {

        System.out.println("test: "+test);
        System.out.println(" запрос: "+search);

        parsMotorland motorland = new parsMotorland();
        HashSet<SparepartEntity> sparepartHashSet = null;
        try {
             sparepartHashSet = motorland.getList(search);
//           sparepartHashSet = parsExistBy.class.newInstance().getListOfPartsByCode("16400-9F910");
        } catch ( IOException e) {
            e.printStackTrace();
        }
        finally {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            for (SparepartEntity sparepartEntity:sparepartHashSet) {
                System.out.println("id: "+sparepartEntity.getIdSparepart());
                session.save(sparepartEntity);
            }
            session.getTransaction().commit();
            session.close();
                System.out.println(" отдал ");
                return sparepartHashSet;
        }
    }
}