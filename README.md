# globalweatherapp

GLOBAL WEATHER APP

This is a backend service which makes a call to exisitng application exposing SOAP API to
1. Get cities for a country
2. Get weather for a city in a country

Build: Maven

Deployment: Build the maven project and run the spring boot application or open command prompt in target directory and run below command:

java -jar weatherapp-0.0.1-SNAPSHOT.jar

Once application has started, hit below URLs on browser/Postman app/curl command in cmd
1. Get cities for a country: http://localhost:8080/getWeather/getCities/Australia
2. Get weather for a city: http:localhost:8080/getWeather?country=Australia&city=Melbourne

For no/invalid input, appropriate error message is displayed.
