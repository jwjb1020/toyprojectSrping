package com.toyproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyproject.service.FestivalService;


@RestController
public class Controller {

    @Autowired(required=true)
    private FestivalService fs;
    
    @GetMapping("feedFestivalData")
    public void setDataInDb(){
        fs.saveFestivalData();
    }
    
}
