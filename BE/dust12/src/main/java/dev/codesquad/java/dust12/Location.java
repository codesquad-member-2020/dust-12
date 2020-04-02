package dev.codesquad.java.dust12;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dev.codesquad.java.dust12.ApiParam.JSON_LIST;
import static dev.codesquad.java.dust12.ApiParam.LOCATION_DATA_KEY;

public class Location {
    Logger logger = LoggerFactory.getLogger(Location.class);

    private String stationName;

    protected Location(String stationName) {
        this.stationName = stationName;
    }

    public String getStationName() {
        return stationName;
    }

    public Location getData(String sb) {
        JSONObject jsonObject = new JSONObject(sb);
        JSONArray jsonArray = (JSONArray) jsonObject.get(JSON_LIST);
        String stationName = jsonArray.getJSONObject(0).get(LOCATION_DATA_KEY).toString();
        logger.info(stationName);
        return new Location(stationName);
    }
}
