package com.omanski.recruitment.service;

import com.omanski.recruitment.model.Airport;
import com.omanski.recruitment.model.GeoPosition;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Service
public class DataService {

    private Airport ai;
    private Airport airport;

    public List<Airport> generateJsons(int size) {

        List<Airport> generatedList = new ArrayList<>();
        Random random = new Random();
        GeoPosition geo_position;
        Airport airport;
        String _type;
        int _id;
        int key;
        String name;
        String fullName;
        String iata_airport_code;
        String type;
        String country;
        float latitude;
        float longitude;
        int location_id;
        boolean inEurope;
        String countryCode;
        boolean coreCountry;
        int distance;

        for (int i = 0; i < size; i++) {
            _type = RandomStringUtils.randomAlphabetic(10);
            _id = getRandomInt(0, 100000000);
            key = getRandomInt(0, 10000);
            name = RandomStringUtils.randomAlphabetic(7);
            fullName = RandomStringUtils.randomAlphabetic(20);
            iata_airport_code = RandomStringUtils.randomAlphabetic(3).toUpperCase(Locale.ROOT);
            type = RandomStringUtils.randomAlphabetic(8);
            country = RandomStringUtils.randomAlphabetic(6);
            latitude = getRandomFloat(-90, 90);
            longitude = getRandomFloat(-180, 180);
            location_id = getRandomInt(0, 1000000);
            inEurope = random.nextBoolean();
            countryCode = RandomStringUtils.randomAlphabetic(2).toUpperCase(Locale.ROOT);
            coreCountry = random.nextBoolean();
            distance = getRandomInt(0, 10000);

            geo_position = new GeoPosition(latitude, longitude);
            airport = new Airport(_type, _id, key, name, fullName, iata_airport_code, type, country, geo_position, location_id, inEurope, countryCode, coreCountry, distance);

            generatedList.add(airport);
        }

        return generatedList;
    }

    private int getRandomInt(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private float getRandomFloat(float min, float max) {
        return (float) ((Math.random() * (max - min)) + min);
    }

    //_type, _id, name, type, latitude, longitude
    public List<String> getBasicData(int size) {
        List<Airport> airportsList = this.generateJsons(size);
        List<String> result = new ArrayList<>();
        for (Airport airport : airportsList) {
            result.add(String.join(", ", airport.get_type(), String.valueOf(airport.get_id()), airport.getType(),
                    String.valueOf(airport.getGeo_position().getLatitude()), String.valueOf(airport.getGeo_position().getLongitude())));
        }

        return result;
    }

    public List<String> getSpecifiedData(int size, List<String> params) throws IllegalAccessException {
        List<Airport> airportsList = this.generateJsons(size);
        List<String> result = new ArrayList<>();
        for (Airport airport : airportsList) {
            String line = "";
            Field[] fields = Airport.class.getDeclaredFields();
            Field[] geoFields = GeoPosition.class.getDeclaredFields();
            for (String param : params) {
                for (Field field : fields) {
                    if (param.equals(field.getName())) {
                        field.setAccessible(true);
                        line += (String.valueOf(field.get(airport) + ", "));
                    }
                }

                for (Field field : geoFields) {
                    if (param.equals(field.getName())) {
                        field.setAccessible(true);
                        line += (String.valueOf(field.get(airport.getGeo_position()) + ", "));
                    }
                }
            }

            result.add(line.substring(0, line.length() - 2));
        }
        return result;
    }
}
