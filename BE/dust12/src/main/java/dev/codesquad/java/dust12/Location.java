package dev.codesquad.java.dust12;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Location {
    Logger logger = LoggerFactory.getLogger(Location.class);

    private final String KEY = "57yLL991deZDKSPyN3LhskktRLww21pZhY84mpit%2FE3OkDPSKH5UJ5MOfnKDp24SOC3zCbISpcRTJnsneWmFYA%3D%3D";
    private final String OPEN_API_URL = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getNearbyMsrstnList";

    private String stationName;

    protected Location(String stationName) {
        this.stationName = stationName;
    }

    public String getStationName() {
        return stationName;
    }

    public Location ok(String stationName) {
        return new Location(stationName);
    }

    public Object getParserData(StringBuilder sb) {
        JSONObject jsonObject = new JSONObject(sb.toString());
        JSONArray jsonArray = (JSONArray) jsonObject.get("list");
        return jsonArray.getJSONObject(0).get(this.stationName);
    }

    public StringBuilder getLocationJsonData(String tmX, String tmY) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(OPEN_API_URL); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("tmX", "UTF-8") + "=" + URLEncoder.encode(tmX, "UTF-8")); /*TM측정방식 X좌표*/
        urlBuilder.append("&" + URLEncoder.encode("tmY", "UTF-8") + "=" + URLEncoder.encode(tmY, "UTF-8")); /*TM측정방식 Y좌표*/
        urlBuilder.append("&" + URLEncoder.encode("_returnType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        logger.info("Response code: {}", conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        return sb;
    }
}
