package com.omanski.recruitment.controller;

import com.omanski.recruitment.model.Airport;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.omanski.recruitment.service.DataService;

import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaTray;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
    public List<String> getBasicData(@PathVariable("size") int size){
        return  dataService.getBasicData(size);
    }

    @GetMapping(value = "/specifiedData/{size}")
    public List<String> getSpecifiedData(@PathVariable("size") int size, @RequestParam List<String> params, HttpServletResponse response) throws IllegalAccessException {
        return  dataService.getSpecifiedData(size, params);
    }

}
