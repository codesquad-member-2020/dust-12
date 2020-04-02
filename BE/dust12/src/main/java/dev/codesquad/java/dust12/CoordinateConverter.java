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

    protected CoordinateConverter(Double tmX, Double tmY) {
        this.tmX = tmX;
        this.tmY = tmY;
    }

    public Double getTmX() {
        return tmX;
    }

    public Double getTmY() {
        return tmY;
    }

    public CoordinateConverter getData(String originJson) {
        JSONObject jsonObject = new JSONObject(originJson);
        JSONArray jsonArray = (JSONArray) jsonObject.get(JSON_DOCUMENTS);
        Double tmX = (Double) jsonArray.getJSONObject(0).get("x");
        Double tmY = (Double) jsonArray.getJSONObject(0).get("y");
        logger.info("arrayResult: {}, tmX: {}, tmY: {}", jsonArray, tmX, tmY);
        return new CoordinateConverter(tmX, tmY);
    }
}
