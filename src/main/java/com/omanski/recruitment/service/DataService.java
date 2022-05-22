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
    public List<List<String>> getBasicData(int size) {
        List<Airport> airportsList = this.generateJsons(size);
        List<List<String>> result = new ArrayList<>();
        for (Airport airport : airportsList) {
            List<String> line = new ArrayList<>();
            line.add(airport.get_type());
            line.add(String.valueOf(airport.get_id()));
            line.add(airport.getName());
            line.add(airport.getType());
            line.add(String.valueOf(airport.getGeo_position().getLatitude()));
            line.add(String.valueOf(airport.getGeo_position().getLongitude()));
        }

        return result;
    }

    public List<String> getSpecifiedData(int size, List<String> params) throws IllegalAccessException {
        List<Airport> airportsList = this.generateJsons(size);

        Map<String, Field> fieldsMap = new HashMap<>();
        Field[] fields = Airport.class.getDeclaredFields();
        Field[] geoFields = GeoPosition.class.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            fieldsMap.put(field.getName(), field);
        }

        for (Field field : geoFields) {
            field.setAccessible(true);
            fieldsMap.put(field.getName(), field);
        }

        List<String> result = new ArrayList<>();

        for (Airport airport : airportsList) {
            String line = "";
            for (String param : params) {
                if (fieldsMap.get(param).get(airport) != null) {
                    line.concat(String.valueOf(fieldsMap.get(param).get(airport)));
                } else if (fieldsMap.get(param).get(airport.getGeo_position()) != null) {
                    line.concat(String.valueOf(fieldsMap.get(param).get(airport.getGeo_position())));
                } else {
                    return null;
                }
            }
            result.add(line);
        }
        return result;
    }
}
