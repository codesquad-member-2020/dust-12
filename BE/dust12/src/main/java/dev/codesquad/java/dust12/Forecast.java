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

public class Forecast {
    Logger logger = LoggerFactory.getLogger(Location.class);

    private final String KEY = "fXJ6ry%2FJVib9COG4WZcL35kCAeiVb%2BPJa%2FTswKpwh4NxBNU6MF35DBBtnjc00TVRDY9hb%2BnubRWuIMrrdpFX2w%3D%3D";
    private final String OPEN_API_URL = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMinuDustFrcstDspth";

    public StringBuilder getForecastJsonData(String searchDate) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(OPEN_API_URL); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("searchDate", "UTF-8") + "=" + URLEncoder.encode(searchDate, "UTF-8"));
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

    public String getParserData(StringBuilder sb, int index, String data) {
        JSONObject jsonObject = new JSONObject(sb.toString());
        JSONArray jsonArray = (JSONArray) jsonObject.get("list");
        return jsonArray.getJSONObject(index).getString(data);
    }

}
