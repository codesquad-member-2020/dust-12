package dev.codesquad.java.dust12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
public class TMController {
    private final Logger logger = LoggerFactory.getLogger(TMController.class);

    @GetMapping("/test")
    public String test() {
        try {
            String openApiData = OpenApiUtils.getCoordinateJson("37.490983", "127.033353");
            CoordinateConverter coordinateConverter = new CoordinateConverter();
            String myData = coordinateConverter.getData(openApiData);
            return myData;
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
