package dev.codesquad.java.dust12;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TMConverter {
    Logger logger = LoggerFactory.getLogger(Location.class);
    private String tmX;
    private String tmY;

    protected TMConverter(String tmX, String tmY) {
        this.tmX = tmX;
        this.tmY = tmY;
    }

    public String getPosX() {
        return tmX;
    }

    public String getPosY() {
        return tmY;
    }

    public Object getExtractData(StringBuilder sb) {
        JSONObject jsonObject = new JSONObject(sb.toString());
        JSONObject jsonResultData = jsonObject.getJSONObject("result");
        String posX = Double.toString(jsonResultData.getDouble("posX"));
        String posY = Double.toString(jsonResultData.getDouble("posY"));

        logger.info("DATA X : {}", posX);
        logger.info("DATA Y : {}", posY);

        logger.info("api total : {}", jsonObject);
        logger.info("result : {}", jsonObject.getJSONObject("result"));
        logger.info("final : {}", jsonObject.getJSONObject("result").getLong("posX"));
        return jsonResultData;
    }

    public StringBuilder getTMJsonData() throws IOException {
        final String accessToken = "70d78286-368e-439e-9768-fb1169cd3d6c";
        final String src = "EPSG:4326";
        final String dst = "EPSG:5179";
        final String opApiUrl = "https://sgisapi.kostat.go.kr/OpenAPI3/transformation/transcoord.json";

        StringBuilder urlBuilder = new StringBuilder(opApiUrl); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("accessToken", "UTF-8") + "=" + accessToken); /*accessToken*/
        urlBuilder.append("&" + URLEncoder.encode("src", "UTF-8") + "=" + URLEncoder.encode(src, "UTF-8")); /*현재 좌표계*/
        urlBuilder.append("&" + URLEncoder.encode("dst", "UTF-8") + "=" + URLEncoder.encode(dst, "UTF-8")); /*바꿀 좌표계*/
        urlBuilder.append("&" + URLEncoder.encode("posX", "UTF-8") + "=" + URLEncoder.encode(this.tmX, "UTF-8")); /*TM 좌표를 얻어올 posX좌표*/
        urlBuilder.append("&" + URLEncoder.encode("posY", "UTF-8") + "=" + URLEncoder.encode(this.tmY, "UTF-8")); /*TM 좌표를 얻어올 posY좌표*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
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
