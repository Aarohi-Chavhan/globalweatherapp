package com.deloitte.integration.weatherapp.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="NewDataSet")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public class GetWeatherResponse {

    @XmlElement(name="Location")
    private String location;

    @XmlElement(name="Time")
    private String time;

    @XmlElement(name="Wind")
    private String wind;

    @XmlElement(name="Visibility")
    private String visibility;

    @XmlElement(name="SkyConditions")
    private String skyConditions;

    @XmlElement(name="Temperature")
    private String temperature;

    @XmlElement(name="DewPoint")
    private String dewPoint;

    @XmlElement(name="RelativeHumidity")
    private String relativeHumidity;

    @XmlElement(name="Status")
    private String status;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getSkyConditions() {
        return skyConditions;
    }

    public void setSkyConditions(String skyConditions) {
        this.skyConditions = skyConditions;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(String dewPoint) {
        this.dewPoint = dewPoint;
    }

    public String getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(String relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
