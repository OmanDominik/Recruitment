package com.omanski.recruitment.controller;

import com.omanski.recruitment.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.omanski.recruitment.service.DataService;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    DataService dataService;

    @GetMapping("/generate/json/{size}")
    public List<Airport> generateList(@PathVariable("size") int size){
        return dataService.generateJsons(size);
    }

    @GetMapping("/basicData/{size}")
    public List<List<String>> getBasicData(@PathVariable("size") int size){
        return dataService.getBasicData(size);
    }

    @GetMapping("/specifiedData/{size}")
    @ResponseBody
    public List<String> getSpecifiedData(@PathVariable("size") int size, @RequestParam List<String> params) throws IllegalAccessException {
        return dataService.getSpecifiedData(size, params);
    }

}
