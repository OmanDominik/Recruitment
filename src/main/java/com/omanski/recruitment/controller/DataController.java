package com.omanski.recruitment.controller;

import com.omanski.recruitment.model.Airport;
import com.omanski.recruitment.service.DataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DataController {

    final
    DataService dataService;
    @GetMapping("/generate/json/{size}")
    public @ResponseBody List<Airport> generateList(@PathVariable("size") int size){
        return dataService.generateJsons(size);
    }


}
