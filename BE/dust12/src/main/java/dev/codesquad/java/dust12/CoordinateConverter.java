package dev.codesquad.java.dust12;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dev.codesquad.java.dust12.ApiParam.*;

public class CoordinateConverter {
    Logger logger = LoggerFactory.getLogger(Location.class);
    private String tmX;
    private String tmY;

    protected CoordinateConverter() {
        this.tmX = "";
        this.tmY = "";
    }

    public String getTmX() {
        return tmX;
    }

    public String getTmY() {
        return tmY;
    }

    public String getData(String originJson) {
        JSONObject jsonObject = new JSONObject(originJson);
        JSONArray jsonArray = (JSONArray) jsonObject.get(JSON_LIST_NAME);

        // 좌표값을 String, Double 어느것으로 할지 결정하기
        String tmX = Double.toString((Double) jsonArray.getJSONObject(0).get("x"));
        Double tmY = (Double) jsonArray.getJSONObject(0).get("y");

        logger.info("api total : {}", jsonObject);
        logger.info("result : {}", jsonArray);
        logger.info("tmX : {}", tmX);
        logger.info("tmY : {}", tmY);

        return jsonArray.toString();
    }
}
