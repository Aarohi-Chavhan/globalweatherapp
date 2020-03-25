package com.deloitte.integration.weatherapp.util;

import org.springframework.stereotype.Component;

import javax.xml.soap.*;

@Component
public class SoapMessageCreator {
    private SOAPMessage createSoapMessage() throws SOAPException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
        SOAPBody soapBody = soapEnvelope.getBody();

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", "http://192.168.99.100:8080/GlobalWeather");

        return soapMessage;
    }

    public SOAPMessage soapMessageToGetCitiesByCountry(String country) throws SOAPException {
        SOAPMessage soapMessage = this.createSoapMessage();
        SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
        SOAPBodyElement element = soapMessage.getSOAPBody().addBodyElement(soapEnvelope.createName("GetCitiesByCountry", "web","http://www.webserviceX.NET"));
        element.addChildElement(soapEnvelope.createName("CountryName", "web", "http://www.webserviceX.NET")).addTextNode(country);
        soapMessage.saveChanges();
        return soapMessage;
    }

    public SOAPMessage soapMessageToGetWeather(String country, String city) throws SOAPException {
        SOAPMessage soapMessage = this.createSoapMessage();
        SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
        SOAPBodyElement element = soapMessage.getSOAPBody().addBodyElement(soapEnvelope.createName("GetWeather", "web","http://www.webserviceX.NET"));
        element.addChildElement(soapEnvelope.createName("CountryName", "web", "http://www.webserviceX.NET")).addTextNode(country);
        element.addChildElement(soapEnvelope.createName("CityName", "web", "http://www.webserviceX.NET")).addTextNode(city);
        soapMessage.saveChanges();
        return soapMessage;
    }
}
