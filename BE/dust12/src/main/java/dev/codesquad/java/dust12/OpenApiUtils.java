package dev.codesquad.java.dust12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static dev.codesquad.java.dust12.ApiParam.*;
import static dev.codesquad.java.dust12.ApiUrl.*;

public class OpenApiUtils {
    public static String getCoordinateJson(Double wgsX, Double wgsY) throws IOException {
        return getOriginJson(requestCoordinateUrl(wgsX, wgsY));
    }

    public static String getLocationJson(Double tmX, Double tmY) throws IOException {
        return getOriginJson(requestLocationUrl(tmX, tmY));
    }

    public static String getDustJson(String stationName) throws IOException {
        return getOriginJson(requestDustUrl(stationName));
    }

    public static String getForecastJson() {
        return "";
    }

    private static String requestCoordinateUrl(Double wgsX, Double wgsY) {
        String requestUrl = KAKAO_COORDINATE_URL + "?"
                + OUTPUT_COORD + "&"
                + X + wgsX + "&"
                + Y + wgsY;
        return requestUrl;
    }

    private static String requestDustUrl(String stationName) {
        String requestUrl = KECO_URL + DUST_URL + "?"
                + DATA_TERM + "&"
                + NUMBER_OF_ROWS + "&"
                + RETURN_JSON + "&"
                + VERSION + "&"
                + STATION_NAME + stationName + "&"
                + SERVICE_KEY + KECO_KEY;
        return requestUrl;
    }

    private static String requestLocationUrl(Double tmX, Double tmY) {
        String requestUrl = KECO_URL + LOCATION_URL + "?"
                + RETURN_JSON + "&"
                + TM_X + tmX + "&"
                + TM_Y + tmY + "&"
                + SERVICE_KEY + KECO_KEY;
        return requestUrl;
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
}
