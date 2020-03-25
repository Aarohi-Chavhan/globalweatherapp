package com.deloitte.integration.weatherapp.handler;

import com.deloitte.integration.weatherapp.exception.GlobalException;
import com.deloitte.integration.weatherapp.model.GetWeatherResponse;
import com.deloitte.integration.weatherapp.model.XmlCityModel;
import com.deloitte.integration.weatherapp.service.WeatherAppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.util.regex.Pattern;

import static org.springframework.web.reactive.function.server.ServerResponse.*;


@Component
@Slf4j
public class WeatherAppHandler {

    Pattern inputPattern = Pattern.compile("^[ A-Za-z]+$");

    @Autowired
    private WeatherAppService weatherAppService;

    public Mono<ServerResponse> getCitiesByCountry(ServerRequest serverRequest) {
        String country = serverRequest.pathVariable("country").trim();
        if(!inputPattern.matcher(country).matches()){
            throw new GlobalException(HttpStatus.BAD_REQUEST, "Please enter valid country!");
        }
        try {
            return ok().contentType(MediaType.APPLICATION_JSON)
                    .body(weatherAppService.getCityByCountry(country), XmlCityModel.class);
        } catch (SOAPException | IOException | JAXBException e) {
            log.error("Exception occurred while making SOAP call or reading SOAP response" + e.getMessage());
            throw new GlobalException(HttpStatus.BAD_REQUEST, "Exception occurred while making SOAP call or reading SOAP response");
        }
    }

    public Mono<ServerResponse> getWeather(ServerRequest serverRequest) {
        String country = serverRequest.queryParam("country").get().trim();
        String city = serverRequest.queryParam("city").get().trim();
        if(!inputPattern.matcher(country).matches() || !inputPattern.matcher(city).matches()){
            throw new GlobalException(HttpStatus.BAD_REQUEST, "Please enter valid country and/or city!");
        }
        try {
            return ok().contentType(MediaType.APPLICATION_JSON)
                    .body(weatherAppService.getWeatherOfCity(country, city), GetWeatherResponse.class);
        } catch (SOAPException | JAXBException | IOException e) {
            log.error("Exception occurred while making SOAP call or reading SOAP response" + e.getMessage());
            throw new GlobalException(HttpStatus.BAD_REQUEST, "Exception occurred while making SOAP call or reading SOAP response");
        }
    }

}
