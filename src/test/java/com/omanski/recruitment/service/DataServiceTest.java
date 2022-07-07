package com.omanski.recruitment.service;

import com.omanski.recruitment.model.Airport;
import com.omanski.recruitment.model.GeoPosition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DataServiceTest {

    @InjectMocks
    DataService dataService = new DataService();


    @Test
    void generateJsons() {
        //given
        int sizeOfListToGenerate = 2;
        Airport sampleAirport = new Airport("uTUclIFUdn", 15, 106, "LeufsTo", "yvjPMgmdYKSiEJUTKFSr",
                "EZP", "AwOFMFUW", "nIkaLW",
                new GeoPosition(-11.0f, -71.2f),
                468, false, "UX", false, 2614);


        try (MockedStatic<Airport> mockedStatic = Mockito.mockStatic(Airport.class)) {

            mockedStatic
                    .when(() -> Airport.getRandomInstance())
                    .thenReturn(sampleAirport);


            //when
            List<Airport> generatedList = dataService.generateJsons(sizeOfListToGenerate);

            //then
            assertThat(generatedList.size()).isEqualTo(sizeOfListToGenerate);
            assertThat(generatedList).isEqualTo(List.of(sampleAirport, sampleAirport));
        }
    }
}