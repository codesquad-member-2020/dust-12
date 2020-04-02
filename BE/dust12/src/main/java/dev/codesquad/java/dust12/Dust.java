package dev.codesquad.java.dust12;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static dev.codesquad.java.dust12.ApiParam.*;

public class Dust {
    Logger logger = LoggerFactory.getLogger(Dust.class);

    private String pm10Grade1h;
    private String pm10Value;
    private String dataTime;

    protected Dust(String pm10Grade1h, String pm10Value, String dataTime) {
        this.pm10Grade1h = pm10Grade1h;
        this.pm10Value = pm10Value;
        this.dataTime = dataTime;
    }

    public String getPm10Grade1h() {
        return pm10Grade1h;
    }

    public String getPm10Value() {
        return pm10Value;
    }

    public String getDataTime() {
        return dataTime;
    }

    public Dust getDustData(String sb, int id) {
        JSONObject jsonObject = new JSONObject(sb);
        JSONArray jsonArray = (JSONArray) jsonObject.get(JSON_LIST);
        String dustGrade = (String) jsonArray.getJSONObject(id).get(PM10_GRADE);
        String dustValue = (String) jsonArray.getJSONObject(id).get(PM10_VALUE);
        String dustMeasuringTime = (String) jsonArray.getJSONObject(id).get(DUST_MEASURING_TIME);
        logger.info("dustGrade: {}, dustValue: {}, dustMeasuringTime: {}", dustGrade, dustValue, dustMeasuringTime);
        return new Dust(dustGrade, dustValue, dustMeasuringTime);
    }
}
