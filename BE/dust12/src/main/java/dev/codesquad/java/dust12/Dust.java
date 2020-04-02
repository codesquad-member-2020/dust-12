package dev.codesquad.java.dust12;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dev.codesquad.java.dust12.ApiParam.*;

public class Dust {
    Logger logger = LoggerFactory.getLogger(Dust.class);

    private String pm10Grade1h;
    private String pm10Value;
    private String dataTime;

    protected Dust() {
        pm10Grade1h = null;
        pm10Value = null;
        dataTime = null;
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

    public String getParserData(String sb) {
        JSONObject jsonObject = new JSONObject(sb);
        JSONArray jsonArray = (JSONArray) jsonObject.get(JSON_LIST);
        return jsonArray.toString();
    }
}
