package com.omanski.recruitment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class GeoPosition {
    private float latitude;
    private float longitude;

}
