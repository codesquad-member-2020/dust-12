package dev.codesquad.java.dust12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static dev.codesquad.java.dust12.ApiParam.*;

@RestController
public class DustController {
    Logger logger = LoggerFactory.getLogger(Dust.class);

    @GetMapping("/dust")
    public ResponseEntity dustValue() throws IOException {
        String stationName = "강남구";
        String openApiData = OpenApiUtils.getDustJson(stationName);

        Dust dust = new Dust(null, null,null);
        List<Dust> dustList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_DATA ; i++) {
            dustList.add(dust.getDustData(openApiData, i));
        }
        return new ResponseEntity(dustList, HttpStatus.OK);
    }
}
