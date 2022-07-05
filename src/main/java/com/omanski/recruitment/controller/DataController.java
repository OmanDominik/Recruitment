package com.omanski.recruitment.controller;

import com.omanski.recruitment.model.Airport;
import com.omanski.recruitment.service.DataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name="Airports generator")
public class DataController {

    final
    DataService dataService;


    @Operation(summary = "Random airports list generator", description = "Generates a list of airports with random properties of the given size")
    @GetMapping("/generate/json/{size}")
    public @ResponseBody List<Airport> generateList(@PathVariable("size") int size){
        return dataService.generateJsons(size);
    }


}
