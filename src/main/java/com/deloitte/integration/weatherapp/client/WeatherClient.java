package com.deloitte.integration.weatherapp.client;

import com.deloitte.integration.weatherapp.model.GetCitiesByCountryResponse;
import com.deloitte.integration.weatherapp.model.GetWeatherResponse;
import com.deloitte.integration.weatherapp.model.XmlCityModel;
import com.deloitte.integration.weatherapp.util.SoapMessageCreator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

@Component
@Slf4j
public class WeatherClient {

    @Autowired
    private SoapMessageCreator soapMessageCreator;

    private final static String SOAP_ENDPOINT = "http://192.168.99.100:8080/GlobalWeather";

    public List<XmlCityModel> getCities(String country) throws SOAPException, IOException, JAXBException {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        // Send SOAP Message to SOAP Server
        log.info("Making SOAP Call for getting cities for Country:" + country);
        SOAPMessage soapResponse = soapConnection.call(soapMessageCreator.soapMessageToGetCitiesByCountry(country), SOAP_ENDPOINT);

        JAXBContext jaxbContext = JAXBContext.newInstance(GetCitiesByCountryResponse.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        StringReader reader = new StringReader(soapResponse.getSOAPBody().getElementsByTagNameNS("http://www.webserviceX.NET","GetCitiesByCountryResponse")
                .item(0).getTextContent()
                .replaceAll("&lt;", "<").replaceAll("&gt;", ">"));
        GetCitiesByCountryResponse result = (GetCitiesByCountryResponse) jaxbUnmarshaller.unmarshal(reader);
        return result.getCityList();
    }

    public GetWeatherResponse getCityWeather(String country, String city) throws SOAPException, JAXBException, IOException {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        // Send SOAP Message to SOAP Server
        log.info("Getting weather for city: "+city + " in Country: "+country);
        SOAPMessage soapResponse = soapConnection.call(soapMessageCreator.soapMessageToGetWeather(country,city), SOAP_ENDPOINT);
        JAXBContext jaxbContext = JAXBContext.newInstance(GetWeatherResponse.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        StringReader formattedResponse = cleanSoapResponse(soapResponse);
        GetWeatherResponse weatherResponse = (GetWeatherResponse) jaxbUnmarshaller.unmarshal(formattedResponse);
        return weatherResponse;
    }

    private StringReader cleanSoapResponse(SOAPMessage soapResponse) throws SOAPException, IOException {
        StringReader reader = new StringReader(soapResponse.getSOAPBody().getElementsByTagNameNS("http://www.webserviceX.NET","GetWeatherResponse").item(0).getTextContent().replaceAll("&lt;", "<").replaceAll("&gt;", ">"));
        String stringResponse = IOUtils.toString(reader);
        if (stringResponse.startsWith("<![CDATA[")) {
            stringResponse = stringResponse.substring(9);
            int i = stringResponse.indexOf("]]>");
            if (i == -1) {
                i = stringResponse.length()-1;
            }
            stringResponse = stringResponse.substring(0, i);
        }
        return new StringReader(stringResponse);
    }



}
