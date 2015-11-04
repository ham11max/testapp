package service;

import DAO.Dao;
import DAO.InsertToDB;
import model.JsonModel;
import model.RequestFromForm;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by HAMMAX on 19.07.2015.
 */
@org.springframework.stereotype.Service("userService")
public class ServiceImpl implements Service {

    @Autowired
    Dao dao;
    InsertToDB insertToDB = new InsertToDB();

    public  JSONObject createJsonFromForm(String key , String echo) throws  JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key" ,key);
        jsonObject.put("echo" , echo);
        return  jsonObject;
    }


    @Override
    public void postJsonAndGetResponse(RequestFromForm requestFromForm) throws IOException, JSONException  , SQLException{
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postRequest = new HttpPost("http://tripcomposer.net/rest/test/countries/get");
        StringEntity input = new StringEntity(createJsonFromForm(requestFromForm.getKey(),requestFromForm.getMessage()).toString());
        input.setContentType("application/json");
        postRequest.setEntity(input);

        HttpResponse response = httpClient.execute(postRequest);

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));

        String output;
        while ((output = bufferedReader.readLine()) != null) {
            insertToDB.insertToDB(parseJson(output));
        }

        httpClient.getConnectionManager().shutdown();

    }
    public List<JsonModel> parseJson(String result) throws JSONException{
        List<JsonModel> allDataFromJson = new ArrayList<JsonModel>();
        JSONObject object = new JSONObject(result);
        JSONArray countries = object.getJSONArray("countries");
        for (int i = 0; i < countries.length(); i++)
        {
            JsonModel jsonModel = new JsonModel();

            String countryName = countries.getJSONObject(i).getString("countryName");
            String countryIsoCode = countries.getJSONObject(i).getString("countryISOCode");
            jsonModel.setCountryName(countryName);
            jsonModel.setCountryIsoCode(countryIsoCode);
            JSONArray cities = countries.getJSONObject(i).getJSONArray("cities");
            List<String> cityNameList = new ArrayList<String>();
            for(int j =0 ; j<cities.length();j++){
                String cityName = cities.getJSONObject(j).getString("cityName");
                cityNameList.add(cityName);
            }
            jsonModel.setCities(cityNameList);

            allDataFromJson.add(jsonModel);
        }

        return  allDataFromJson;
    }
}
