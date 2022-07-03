package com.omanski.recruitment.service;

import com.omanski.recruitment.model.Airport;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {

    public List<Airport> generateJsons(int size) {

        List<Airport> generatedList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            generatedList.add(Airport.getRandomInstance());
        }

        return generatedList;
    }


}
