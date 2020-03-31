package dev.codesquad.java.dust12;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

@RestController
public class LocationController {
    private final String KEY = "57yLL991deZDKSPyN3LhskktRLww21pZhY84mpit%2FE3OkDPSKH5UJ5MOfnKDp24SOC3zCbISpcRTJnsneWmFYA%3D%3D";
    private final String OPEN_API_URL = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getNearbyMsrstnList?tmX=244148.546388&tmY=412423.75772&_returnType=json&ServiceKey=";
    private final String OPEN_API_URL2 = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getNearbyMsrstnList";
    @GetMapping("/")
    public String test() {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(OPEN_API_URL + KEY);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine;
            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine + "\n");
            }
            urlConnection.disconnect();
            return result.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/test")
    public String test2() {
        try {
            StringBuilder urlBuilder = new StringBuilder(OPEN_API_URL2); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + KEY); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("tmX", "UTF-8") + "=" + URLEncoder.encode("244148.546388", "UTF-8")); /*TM측정방식 X좌표*/
            urlBuilder.append("&" + URLEncoder.encode("tmY", "UTF-8") + "=" + URLEncoder.encode("412423.75772", "UTF-8")); /*TM측정방식 Y좌표*/
            urlBuilder.append("&" + URLEncoder.encode("_returnType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
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
            System.out.println(sb.toString());
            return sb.toString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
