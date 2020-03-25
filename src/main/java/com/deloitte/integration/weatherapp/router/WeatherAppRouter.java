package com.deloitte.integration.weatherapp.router;

import com.deloitte.integration.weatherapp.handler.WeatherAppHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class WeatherAppRouter {

    @Bean
    public RouterFunction<ServerResponse> route(WeatherAppHandler handler){
        return RouterFunctions.route(RequestPredicates.GET("/getWeather")
        .and(accept(MediaType.APPLICATION_JSON)), handler::getWeather)
                .andRoute(RequestPredicates.GET("/getWeather/getCities/{country}")
                        .and(accept(MediaType.APPLICATION_JSON)), handler::getCitiesByCountry);
    }
}
