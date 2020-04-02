package dev.codesquad.java.dust12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.IOException;

@RestController
public class TMController {
    private final Logger logger = LoggerFactory.getLogger(TMController.class);

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
}
