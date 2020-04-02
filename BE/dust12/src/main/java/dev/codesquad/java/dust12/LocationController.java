package dev.codesquad.java.dust12;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import static dev.codesquad.java.dust12.ApiParam.*;

@RestController
public class LocationController {

    @GetMapping("/location")
    public ResponseEntity location() throws IOException {
        String openApiData = OpenApiUtils.getLocationJson("244148.546388", "412423.75772");
        Location location = new Location(null);
        location = location.getData(openApiData);
        return new ResponseEntity(location, HttpStatus.OK);
    }
}
