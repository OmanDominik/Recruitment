package com.omanski.recruitment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
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


}
