package dev.codesquad.java.dust12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static dev.codesquad.java.dust12.ApiParam.NUMBER_OF_DATA;

@RestController
@CrossOrigin("*")
public class ApiController {
    private final Logger logger = LoggerFactory.getLogger(ApiController.class);
    /*
    //  converter example
    //  wgsX = 127.49816064433354 wgsY =37.21265944475513
    //  tmX = 244148.546388 tmY = 412423.75772
     */

    @GetMapping("/location")
    public ResponseEntity<Location> location(@RequestParam Double wgsX, @RequestParam Double wgsY) throws IOException {
        CoordinateConverter converter = converter(wgsX, wgsY);
        Double tmX = converter.getTmX();
        Double tmY = converter.getTmY();

        String openApiData = OpenApiUtils.getLocationJson(tmX, tmY);
        Location location = new Location(null);
        location = location.getData(openApiData);

        // ResponseEntity.ok(location);
        return new ResponseEntity(location, HttpStatus.OK);
    }

    private CoordinateConverter converter(Double wgsX, Double wgsY) throws IOException {
        String openApiData = OpenApiUtils.getCoordinateJson(wgsX, wgsY);
        CoordinateConverter converter = new CoordinateConverter(null, null);
        return converter.getData(openApiData);
    }

    @GetMapping("/dust")
    public ResponseEntity dust(@RequestParam String stationName) throws IOException {
        String openApiData = OpenApiUtils.getDustJson(stationName);
        Dust dust = new Dust(null, null,null);
        List<Dust> dustList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_DATA ; i++) {
            dustList.add(dust.getData(openApiData, i));
        }
        return new ResponseEntity(dustList, HttpStatus.OK);
    }

    @GetMapping("/forecast")
    public ResponseEntity<Forecast> forecast() throws IOException {
        String openApiData = OpenApiUtils.getForecastJson();
        Forecast forecast = new Forecast(null, null, null, null, null, null, null);
        forecast = forecast.getData(openApiData);
        return new ResponseEntity(forecast, HttpStatus.OK);
    }
}
