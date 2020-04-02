package dev.codesquad.java.dust12;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DustController {

    @GetMapping("/dust")
    public String dustValue() throws IOException {
        String stationName = "강남구";
        String openApiData = OpenApiUtils.getDustJson(stationName);
        Dust dust = new Dust();

        return dust.getParserData(openApiData);
    }
}
