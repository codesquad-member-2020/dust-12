package dev.codesquad.java.dust12;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dev.codesquad.java.dust12.ApiParam.*;

public class Forecast {
    Logger logger = LoggerFactory.getLogger(Forecast.class);

    private String informOverall;
    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;
    private String dataTime;
    private String informGrade;
    private String informCode;

    public Forecast(String informOverall, String imageUrl1, String imageUrl2, String imageUrl3, String dataTime, String informGrade, String informCode) {
        this.informOverall = informOverall;
        this.imageUrl1 = imageUrl1;
        this.imageUrl2 = imageUrl2;
        this.imageUrl3 = imageUrl3;
        this.dataTime = dataTime;
        this.informGrade = informGrade;
        this.informCode = informCode;
    }

    public String getInformOverall() {
        return informOverall;
    }

    public String getImageUrl1() {
        return imageUrl1;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public String getImageUrl3() {
        return imageUrl3;
    }

    public String getDataTime() {
        return dataTime;
    }

    public String getInformGrade() {
        return informGrade;
    }

    public String getInformCode() {
        return informCode;
    }

    public Forecast getData(String sb) {
        JSONObject jsonObject = new JSONObject(sb);
        JSONArray jsonArray = (JSONArray) jsonObject.get(JSON_LIST);

        String informOverall = (String) jsonArray.getJSONObject(0).get(INFORM_OVERALL);
        String imageUrl1 = (String) jsonArray.getJSONObject(0).get(IMAGE_URL_1);
        String imageUrl2 = (String) jsonArray.getJSONObject(0).get(IMAGE_URL_2);
        String imageUrl3 = (String) jsonArray.getJSONObject(0).get(IMAGE_URL_3);
        String dataTime = (String) jsonArray.getJSONObject(0).get(DUST_MEASURING_TIME);
        String informGrade = (String) jsonArray.getJSONObject(0).get(INFORM_GRADE);
        String informCode = (String) jsonArray.getJSONObject(0).get(INFORM_CODE);
        return new Forecast(informOverall, imageUrl1, imageUrl2, imageUrl3, dataTime, informGrade, informCode);
    }
}
