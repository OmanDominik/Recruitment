package com.omanski.recruitment.service;

import com.omanski.recruitment.model.Airport;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@Getter
@Setter
@ConfigurationProperties(prefix="generating")
public class DataService {

    private String url;

    public List<Airport> generateJsons(int size) {

        final String uri = url + "generate/json/" + size;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Airport[]> response = restTemplate.getForEntity(uri, Airport[].class);

        Airport[] generatedList = response.getBody();

        return List.of(generatedList);
    }

    //_type, _id, name, type, latitude, longitude
    public List<String> getBasicData(int size) {
        List<Airport> airportsList = this.generateJsons(size);

        List<String> result = new ArrayList<>();
        for (Airport airport : airportsList) {
            List<String> line = new ArrayList<>();

            line.add(airport.get_type());
            line.add(String.valueOf(airport.get_id()));
            line.add(airport.getName());
            line.add(airport.getType());
            line.add(String.valueOf(airport.getGeo_position().getLatitude()));
            line.add(String.valueOf(airport.getGeo_position().getLongitude()));

            result.add(String.join(",", line));
        }
        return result;
    }

    public List<String> getSpecifiedData(int size, List<String> params) throws IllegalAccessException {
        List<Airport> airportsList = this.generateJsons(size);
        List<String> result = new ArrayList<>();

        for (Airport airport : airportsList) {
            List<String> line = new ArrayList<>();
            for (String param : params) {
                if (Airport.fieldsMap.containsKey(param)) {
                    line.add(String.valueOf(Airport.fieldsMap.get(param).get(airport)));
                } else if(Airport.geoFieldsMap.containsKey(param)){
                    line.add(String.valueOf(Airport.geoFieldsMap.get(param).get(airport.getGeo_position())));
                }
            }
            result.add(String.join(",", line));
        }
        return result;
    }

    public List<String> calculateGivenOperations(List<String> params) throws IllegalAccessException {
        List<String> results = new ArrayList<>();

        Airport airport = this.generateJsons(1).get(0);

        System.out.println(airport.toString());

        for (String operation: params) {
            Double result;

            for (String parameterName: Airport.fieldsMap.keySet()) {
                if(operation.indexOf(parameterName) != -1){
                    operation = operation.replace(parameterName, String.valueOf(Airport.fieldsMap.get(parameterName).get(airport)));
                }
            }
            for (String parameterName: Airport.geoFieldsMap.keySet()) {
                if(operation.indexOf(parameterName) != -1){
                    operation = operation.replace(parameterName, String.valueOf(Airport.geoFieldsMap.get(parameterName).get(airport.getGeo_position())));
                }
            }

            System.out.println(operation);

            try {
                ExpressionParser parser = new SpelExpressionParser();
                result = parser.parseExpression(operation).getValue(Double.class);
                System.out.println(result);
                results.add(String.valueOf(result));
            }catch (SpelEvaluationException | SpelParseException see){
                results.add("Illegal operation called: " + operation);
            }

        }

        return results;
    }
}
