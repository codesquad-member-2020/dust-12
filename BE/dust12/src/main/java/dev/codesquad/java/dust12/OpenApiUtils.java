package dev.codesquad.java.dust12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static dev.codesquad.java.dust12.ApiParam.*;
import static dev.codesquad.java.dust12.ApiUrl.*;

public class OpenApiUtils {
    public static String getCoordinateJson(String wgsX, String wgsY) throws IOException {
        return getOriginJson(requestUrl(wgsX, wgsY));
    }

    public static String getLocationJson() {
        return "";
    }

    public static String getDustJson() {
        return "";
    }

    public static String getForecastJson() {
        return "";
    }

    private static String getOriginJson(String inputUrl) throws IOException {
        //url connection
        URL url = new URL(inputUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        //set header
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty(KAKAO_AUTHORIZTION, KAKAO_KEY);

        //build String data
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        urlConnection.disconnect();
        return sb.toString();
    }

    private static String requestUrl(String wgsX, String wgsY) {
        String requestUrl = KAKAO_COORDINATE_URL + "?"
                + OUTPUT_COORD + "&"
                + X + wgsX + "&"
                + Y + wgsY;
        return requestUrl;
    }
}
