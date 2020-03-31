package dev.codesquad.java.dust12;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class LocationController {

    @GetMapping("/location")
    public ResponseEntity<String> test() {
        try {
            Location location = new Location("stationName");
            StringBuilder sb = location.getLocationJsonData("244148.546388", "412423.75772");
            String myData = (String) location.getParserData(sb);
            return new ResponseEntity(location.ok(myData), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
