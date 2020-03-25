package com.deloitte.integration.weatherapp.service;

import com.deloitte.integration.weatherapp.client.WeatherClient;
import com.deloitte.integration.weatherapp.model.GetWeatherResponse;
import com.deloitte.integration.weatherapp.model.XmlCityModel;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.util.List;

@Service
public class WeatherAppService {

    private final ApplicationEventPublisher applicationEventPublisher;

    private WeatherClient weatherClient;

    public WeatherAppService(ApplicationEventPublisher applicationEventPublisher, WeatherClient weatherClient) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.weatherClient = weatherClient;
    }

    public Flux<List<XmlCityModel>> getCityByCountry(String country) throws SOAPException, IOException, JAXBException {
        return Flux.just(weatherClient.getCities(country));
    }

    public Mono<GetWeatherResponse> getWeatherOfCity(String country, String city) throws JAXBException, SOAPException, IOException {
        return Mono.just(weatherClient.getCityWeather(country, city));
    }
}
