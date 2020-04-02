package dev.codesquad.java.dust12;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dev.codesquad.java.dust12.ApiParam.*;

public class CoordinateConverter {
    Logger logger = LoggerFactory.getLogger(Location.class);
    private Double tmX;
    private Double tmY;

    protected CoordinateConverter() {
        this.tmX = null;
        this.tmY = null;
    }

    public Double getTmX() {
        return tmX;
    }

    public Double getTmY() {
        return tmY;
    }

    public String getData(String originJson) {
        JSONObject jsonObject = new JSONObject(originJson);
        JSONArray jsonArray = (JSONArray) jsonObject.get(JSON_LIST_NAME);

        this.tmX = (Double) jsonArray.getJSONObject(0).get("x");
        this.tmY = (Double) jsonArray.getJSONObject(0).get("y");

        logger.info("api total : {}", jsonObject);
        logger.info("result : {}", jsonArray);
        logger.info("tmX : {}", tmX);
        logger.info("tmY : {}", tmY);

        return jsonArray.toString();
    }
}
