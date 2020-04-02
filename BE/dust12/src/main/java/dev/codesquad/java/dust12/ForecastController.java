package dev.codesquad.java.dust12;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
public class ForecastController {
    Logger logger = LoggerFactory.getLogger(Location.class);

    @GetMapping("/forecast")
    public String forecast() {
        try {
        Forecast forecast = new Forecast();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        String searchDate = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        StringBuilder openApiJsonData = forecast.getForecastJsonData(searchDate);

        String informGrade = forecast.getParserData(openApiJsonData, 0, "informGrade");
        String informOverall = forecast.getParserData(openApiJsonData, 0, "informOverall");
        String imageUrl1 = forecast.getParserData(openApiJsonData, 0, "imageUrl1");
        String imageUrl2 = forecast.getParserData(openApiJsonData, 0, "imageUrl2");
        String imageUrl3 = forecast.getParserData(openApiJsonData, 0, "imageUrl3");
        String dataTime = forecast.getParserData(openApiJsonData, 0, "dataTime");
        String informCode = forecast.getParserData(openApiJsonData, 0, "informCode");
        String informData = forecast.getParserData(openApiJsonData, 0, "informData");

        jsonObject.put("informGrade", informGrade);
        jsonObject.put("informOverall", informOverall);
        jsonObject.put("imageUrl1", imageUrl1);
        jsonObject.put("imageUrl2", imageUrl2);
        jsonObject.put("imageUrl3", imageUrl3);
        jsonObject.put("dataTime", dataTime);
        jsonObject.put("informCode", informCode);
        jsonObject.put("informData", informData);
        jsonArray.put(0, jsonObject);

        return jsonObject.toString();
        } catch (IOException e) {
            e.getMessage();
            return "";
        }
    }
}
