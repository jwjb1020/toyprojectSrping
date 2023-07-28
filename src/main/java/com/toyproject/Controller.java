package com.toyproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyproject.service.FestivalService;
import com.toyproject.service.FestivalServiceExel;


@RestController
public class Controller {

    @Autowired(required=true)
    private FestivalService fs;
    @Autowired
    private FestivalServiceExel fse;
    
    @GetMapping("feedFestivalData")
    public void setDataInDb(){
        // fs.saveFestivalData();
        fse.ExcelFileRead();
    }
    
}
