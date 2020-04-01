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
    public Object test() {
        try {
            TMConverter tmConverter = new TMConverter("37.490983", "127.033353");
            StringBuilder openApiData = tmConverter.getOriginJson(); // openApi String 가져오는 클래스 새로 정의하기
            Object myData = tmConverter.getData(openApiData);
            return openApiData.toString();//myData.toString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
