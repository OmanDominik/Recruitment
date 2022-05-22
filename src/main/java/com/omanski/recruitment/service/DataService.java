package com.omanski.recruitment.service;

import com.omanski.recruitment.model.Airport;
import com.omanski.recruitment.model.GeoPosition;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

@Service
public class DataService {

    public List<Airport> generateJsons(int size) {

        List<Airport> generatedList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            generatedList.add(Airport.getRandomInstance());
        }

        return generatedList;
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
}
