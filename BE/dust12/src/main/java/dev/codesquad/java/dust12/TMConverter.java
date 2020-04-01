package dev.codesquad.java.dust12;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static dev.codesquad.java.dust12.ApiParam.*;
import static dev.codesquad.java.dust12.ApiUrl.*;

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

    public Object getData(StringBuilder sb) {
        JSONObject jsonObject = new JSONObject(sb.toString());
        //JSONObject jsonResultData = (JSONObject) jsonObject.get("x");
        //String posX = Double.toString(jsonResultData.getDouble("x"));
        //String posY = Double.toString(jsonResultData.getDouble("y"));

//        logger.info("DATA X : {}", posX);
//        logger.info("DATA Y : {}", posY);
        logger.info("api total : {}", jsonObject);
        //logger.info("result : {}", jsonObject.getJSONObject("documents"));
//        logger.info("final : {}", jsonObject.getJSONObject("documents").getLong("x"));
        return jsonObject;
    }

    public String getBuiltUrl() throws UnsupportedEncodingException {
        StringBuilder urlBuilder = new StringBuilder(KAKAO_COORDINATE_URL);
        urlBuilder.append("?");
        urlBuilder.append("&" + URLEncoder.encode("output_coord", "UTF-8") + "=" + URLEncoder.encode(OUTPUT_COORD, "UTF-8")); /*TM 좌표를 얻어올 posX좌표*/
        urlBuilder.append("&" + URLEncoder.encode("x", "UTF-8") + "=" + URLEncoder.encode("37.490983", "UTF-8")); /*TM 좌표를 얻어올 posX좌표*/
        urlBuilder.append("&" + URLEncoder.encode("y", "UTF-8") + "=" + URLEncoder.encode("127.033353", "UTF-8")); /*TM 좌표를 얻어올 posY좌표*/
        return urlBuilder.toString();
    }

    public StringBuilder getOriginJson() throws IOException {
        //url connection
        URL url = new URL(getBuiltUrl());
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        //set header
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("Authorization", KAKAO_KEY);

        //build String data
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        urlConnection.disconnect();
        return sb;
    }
}
