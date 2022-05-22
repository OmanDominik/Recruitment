package com.omanski.recruitment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

@Getter
@Setter

public class Airport {
    private String _type;
    private int _id;
    private int key;
    private String name;
    private String fullName;
    private String iata_airport_code;
    private String type;
    private String country;
    private GeoPosition geo_position;
    private int location_id;
    private boolean inEurope;
    private String countryCode;
    private boolean coreCountry;
    private int distance;

    public Airport(String _type, int _id, int key, String name, String fullName, String iata_airport_code, String type,
                   String country, GeoPosition geo_position, int location_id, boolean inEurope, String countryCode,
                   boolean coreCountry, int distance) {
        this._type = _type;
        this._id = _id;
        this.key = key;
        this.name = name;
        this.fullName = fullName;
        this.iata_airport_code = iata_airport_code;
        this.type = type;
        this.country = country;
        this.geo_position = geo_position;
        this.location_id = location_id;
        this.inEurope = inEurope;
        this.countryCode = countryCode;
        this.coreCountry = coreCountry;
        this.distance = distance;
    }


    public static Airport getRandomInstance(){
        Random random = new Random();
        GeoPosition geo_position;

        String _type = RandomStringUtils.randomAlphabetic(10);
        int _id = getRandomInt(0, 100000000);
        int key = getRandomInt(0, 10000);
        String name = RandomStringUtils.randomAlphabetic(7);
        String fullName = RandomStringUtils.randomAlphabetic(20);
        String iata_airport_code = RandomStringUtils.randomAlphabetic(3).toUpperCase(Locale.ROOT);
        String type = RandomStringUtils.randomAlphabetic(8);
        String country = RandomStringUtils.randomAlphabetic(6);
        float latitude = getRandomFloat(-90, 90);
        float longitude = getRandomFloat(-180, 180);
        int location_id = getRandomInt(0, 1000000);
        boolean inEurope = random.nextBoolean();
        String countryCode = RandomStringUtils.randomAlphabetic(2).toUpperCase(Locale.ROOT);
        boolean coreCountry = random.nextBoolean();
        int distance = getRandomInt(0, 10000);

        geo_position = new GeoPosition(latitude, longitude);

        return new Airport(_type, _id, key, name, fullName, iata_airport_code, type, country, geo_position, location_id, inEurope, countryCode, coreCountry, distance);
    }

    private static int getRandomInt(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private static float getRandomFloat(float min, float max) {
        return (float) ((Math.random() * (max - min)) + min);
    }
}
