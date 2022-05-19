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
    public ResponseEntity<List<Airport>> generateList(@PathVariable("size") int size){
        return new ResponseEntity<List<Airport>>(dataService.generateJsons(size), HttpStatus.OK);
    }

    @GetMapping("/basicData/{size}")
    public List<String> getBasicData(@PathVariable("size") int size){
        return dataService.getBasicData(size);
    }

    @GetMapping("/specifiedData/{size}")
    @ResponseBody
    public List<String> getSpecifiedData(@PathVariable("size") int size, @RequestParam List<String> params){
        return dataService.getSpecifiedData(size, params);
    }

}
