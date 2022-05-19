package com.omanski.recruitment.controller;

import com.omanski.recruitment.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.omanski.recruitment.service.DataService;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    DataService dataService;

    @GetMapping("/generate/json")
    public ResponseEntity<List<Airport>> generateList(){
        return new ResponseEntity<List<Airport>>(dataService.generateJsons(5), HttpStatus.OK);
    }

    @GetMapping("*") public String test() { return "test"; }

}
