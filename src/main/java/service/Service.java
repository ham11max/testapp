package service;

import model.JsonModel;
import model.RequestFromForm;
import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by HAMMAX on 19.07.2015.
 */
public interface Service {

    void postJsonAndGetResponse(RequestFromForm requestFromForm) throws IOException, JSONException, SQLException;

    List<JsonModel> parseJson(String result) throws JSONException;

}
