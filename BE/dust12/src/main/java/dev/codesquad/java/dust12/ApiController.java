package dev.codesquad.java.dust12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static dev.codesquad.java.dust12.ApiParam.NUMBER_OF_DATA;

@RestController
public class ApiController {
    private final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/location")
    public ResponseEntity location(@RequestParam String wgsX, @RequestParam String wgsY) throws IOException {
        // 127.49816064433354, 37.21265944475513
        String openApiData2 = OpenApiUtils.getCoordinateJson(wgsX, wgsY);
        CoordinateConverter coordinateConverter = new CoordinateConverter();
        coordinateConverter.getData(openApiData2);
        Double tmX = coordinateConverter.getTmX();
        Double tmY = coordinateConverter.getTmY();

        // "244148.546388", "412423.75772"
        String openApiData = OpenApiUtils.getLocationJson(tmX, tmY);
        Location location = new Location(null);
        location = location.getData(openApiData);
        return new ResponseEntity(location, HttpStatus.OK);
        // ResponseEntity.ok(location);
    }

    @GetMapping("/test")
    public String test() {
        try {
            String openApiData = OpenApiUtils.getCoordinateJson("127.49816064433354", "37.21265944475513");
            CoordinateConverter coordinateConverter = new CoordinateConverter();
            String myData = coordinateConverter.getData(openApiData);
            Double x = coordinateConverter.getTmX();
            Double y = coordinateConverter.getTmY();
            logger.info("x {} , y {}",x,y);
            return myData;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/dust")
    public ResponseEntity dust() throws IOException {
        String stationName = "강남구";
        String openApiData = OpenApiUtils.getDustJson(stationName);

        Dust dust = new Dust(null, null,null);
        List<Dust> dustList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_DATA ; i++) {
            dustList.add(dust.getData(openApiData, i));
        }
        return new ResponseEntity(dustList, HttpStatus.OK);
    }
}
