package com.deloitte.integration.weatherapp.exception;

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    public GlobalErrorAttributes() {
        super(false);
    }

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(request, includeStackTrace);

        if (getError(request) instanceof GlobalException) {
            GlobalException ex = (GlobalException) getError(request);
            map.put("Exception", ex.getClass().getSimpleName());
            map.put("Message", ex.getMessage());
            map.put("Status", ex.getStatus().value());
            map.put("Error", ex.getStatus().getReasonPhrase());

            return map;
        }

        map.put("Exception", "SystemException");
        map.put("Message", "System Error, Check logs for further information!");
        map.put("Status", "500");
        map.put("Error", "System Error");
        return map;
    }
}
