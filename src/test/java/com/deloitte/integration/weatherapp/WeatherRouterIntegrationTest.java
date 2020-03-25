package com.deloitte.integration.weatherapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherRouterIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testGetCitiesByCountry(){
        webTestClient.get().uri("/getWeather/getCities/Australia")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testGetWeather(){
        webTestClient.get().uri("/getWeather?country=Australia&city=Sydney")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testGetCitiesByCountrt_WhenNoCountrySupplied(){
        webTestClient.get().uri("/getWeather/getCities")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void testGetWeather_WhenNoParametersSupplied(){
        webTestClient.get().uri("/getWeather")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest();
    }
}
