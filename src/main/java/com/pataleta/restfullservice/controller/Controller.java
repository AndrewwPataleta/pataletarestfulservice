package com.pataleta.restfullservice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import com.pataleta.restfullservice.model.Sparepart;
import org.springframework.web.bind.annotation.*;
import com.pataleta.restfullservice.Service.impl.*;

@RestController
class GreetingController {

//    @RequestParam(value="name", required=false, defaultValue="World") String name

    @RequestMapping(value = "/parts/{search}")
    public HashSet<Sparepart> greeting(@PathVariable("search") String search) {

        System.out.println(" запрос: "+search);
        parsMotorland motorland = new parsMotorland();
        HashSet<Sparepart> sparepartHashSet = null;
        try {
         //  sparepartHashSet = motorland.getList(search);
           sparepartHashSet = parsExistBy.class.newInstance().getListOfParts();
        } catch ( IOException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println(" отдал ");
            return sparepartHashSet;
        }
    }
}