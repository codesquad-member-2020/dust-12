package dev.codesquad.java.dust12;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static dev.codesquad.java.dust12.ApiUrl.*;
import static dev.codesquad.java.dust12.ApiUrl.KECO_KEY;

public class OpenApiUrl {
    public static String requestCoordinateUrl(Double wgsX, Double wgsY) {
        String requestUrl = KAKAO_COORDINATE_URL + "?"
                + OUTPUT_COORD + "&"
                + X + wgsX + "&"
                + Y + wgsY;
        return requestUrl;
    }

    public static String requestDustUrl(String stationName) {
        String requestUrl = KECO_URL + DUST_URL + "?"
                + DATA_TERM + "&"
                + NUMBER_OF_ROWS + "&"
                + RETURN_JSON + "&"
                + VERSION + "&"
                + STATION_NAME + stationName + "&"
                + SERVICE_KEY + KECO_KEY;
        return requestUrl;
    }

    public static String requestLocationUrl(Double tmX, Double tmY) {
        String requestUrl = KECO_URL + LOCATION_URL + "?"
                + RETURN_JSON + "&"
                + TM_X + tmX + "&"
                + TM_Y + tmY + "&"
                + SERVICE_KEY + KECO_KEY;
        return requestUrl;
    }

    public static String requestForecastUrl() {
        String requestUrl = KECO_URL + FORECAST_URL + "?"
                + RETURN_JSON + "&"
                + SEARCH_DATE + seoulTime() + "&"
                + SERVICE_KEY + KECO_KEY;
        return requestUrl;
    }

    private static String seoulTime() {
        return ZonedDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
