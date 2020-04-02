package dev.codesquad.java.dust12;

public class ApiUrl {
    //* KAKAO COORDINATE API *//
    public static final String KAKAO_COORDINATE_URL = "https://dapi.kakao.com/v2/local/geo/transcoord.json";
    public static final String KAKAO_AUTHORIZTION = "Authorization";
    public static final String KAKAO_KEY = "KakaoAK 982d4b9bc2a1feb105e11fb5f5aeec1c";

    public static final String OUTPUT_COORD = "output_coord=TM";
    public static final String X = "x=";
    public static final String Y = "y=";

    //* KOREA ECO API *//
    public static final String KECO_URL = "http://openapi.airkorea.or.kr/openapi/services/rest";
    public static final String KECO_KEY = "57yLL991deZDKSPyN3LhskktRLww21pZhY84mpit%2FE3OkDPSKH5UJ5MOfnKDp24SOC3zCbISpcRTJnsneWmFYA%3D%3D";
    public static final String RETURN_JSON = "_returnType=json";
    public static final String SERVICE_KEY = "ServiceKey=";

    // LOCATION
    public static final String LOCATION_URL = "/MsrstnInfoInqireSvc/getNearbyMsrstnList";
    public static final String TM_X = "tmX=";
    public static final String TM_Y = "tmY=";


    // DUST
    public static final String DUST_URL = "/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty";
    public static final String STATION_NAME = "stationName=";
    public static final String DATA_TERM = "dataTerm=daily";
    public static final String NUMBER_OF_ROWS = "numOfRows=24";
    public static final String VERSION = "ver=1.3";

    // FORECAST
    public static final String FORECAST_URL = "/ArpltnInforInqireSvc/getMinuDustFrcstDspth";
    public static final String SEARCH_DATE = "searchDate=";
}
