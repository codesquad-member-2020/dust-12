package dev.codesquad.java.dust12;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class LocationController {

    @GetMapping("/location")
    public ResponseEntity<String> getNearByStation() {
        // @RequestBody String tmX, @RequestBody String tmY
        final String dataKey = "stationName";
        final String tempTmX = "244148.546388";
        final String tempTmY = "412423.75772";
        try {
            Location location = new Location(dataKey);
            StringBuilder openApiJsonData = location.getLocationJsonData(tempTmX, tempTmY);
            String myData = (String) location.getParserData(openApiJsonData);
            return new ResponseEntity(location.ok(myData), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
