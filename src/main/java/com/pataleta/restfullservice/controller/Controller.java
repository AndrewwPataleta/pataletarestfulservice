package com.pataleta.restfullservice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import com.pataleta.restfullservice.model.Auto;
import org.springframework.web.bind.annotation.*;
import com.pataleta.restfullservice.Service.impl.*;

@RestController
class GreetingController {

//    @RequestParam(value="name", required=false, defaultValue="World") String name

    @RequestMapping(value = "/parts/{search}", method = RequestMethod.GET)
    public void greeting(@PathVariable("search") String search) {
        System.out.println(search);
        try {
            parsMotorland.class.newInstance().getList(search);
        } catch (IllegalAccessException | IOException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}