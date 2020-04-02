package dev.codesquad.java.dust12;

import static dev.codesquad.java.dust12.ApiParam.NUMBER_OF_DATA;

public class ApiParam {
    // KAKAO COORDINATE API
    public static final String OUTPUT_COORD = "output_coord=TM";
    public static final String JSON_DOCUMENTS = "documents";
    public static final String X = "x=";
    public static final String Y = "y=";

    // KOREA ECO API
    public static final String LOCATION_DATA_KEY = "stationName";
    public static final String JSON_LIST = "list";
    public static final String RETURN_JSON = "_returnType=json";
    public static final String SERVICE_KEY = "ServiceKey=";

    // LOCATION
    public static final String TM_X = "tmX=";
    public static final String TM_Y = "tmY=";


    // DUST
    public static final String STATION_NAME = "stationName=";
    public static final String DATA_TERM = "dataTerm=daily";
    public static final String NUMBER_OF_ROWS = "numOfRows=24";
    public static final int NUMBER_OF_DATA = 24;
    public static final String VERSION = "ver=1.3";

    public static final String PM10_GRADE = "pm10Grade1h";
    public static final String PM10_VALUE = "pm10Value";
    public static final String DUST_MEASURING_TIME = "dataTime";
}
