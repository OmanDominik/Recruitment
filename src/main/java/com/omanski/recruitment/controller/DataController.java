package com.omanski.recruitment.controller;

import com.omanski.recruitment.model.Airport;
import com.omanski.recruitment.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DataController {

    final
    DataService dataService;
    @GetMapping("/basicData/{size}")
    public List<String> getBasicData(@PathVariable("size") int size){
        return  dataService.getBasicData(size);
    }

    @GetMapping("/specifiedData/{size}")
    public List<String> getSpecifiedData(@PathVariable("size") int size, @RequestParam List<String> params) throws IllegalAccessException {
        return  dataService.getSpecifiedData(size, params);
    }

    @GetMapping("/mathematicalOperations")
    public List<String> calculateOperations(@RequestParam List<String> params) throws IllegalAccessException {
        return  dataService.calculateGivenOperations(params);
    }

}
