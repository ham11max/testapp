package DAO;

import model.JsonModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by HAMMAX on 03.11.2015.
 */
public class InsertToDB {

    Connection getDBConnection(String url, String user, String pass) {

        System.out.println("-------- Start connection to DB-------");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {

            System.out.println("Fail!");
            e.printStackTrace();

        }
        try {

            if (connection != null) {
                  System.out.println("OK!");
            } else {
                System.out.println("Fail!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void  insertToDB(List<JsonModel> jsonModels) throws SQLException{
        String dbURL = "jdbc:postgresql://127.0.0.1:5432/testapp";
        String user = "postgres";
        String password = "1111";

        Connection connection = null;
        PreparedStatement preparedStatementCountry = null;
        PreparedStatement preparedStatementCity = null;

        String countrySql = "INSERT INTO countries (countryname , countryisocode) VALUES (? , ?  );";
        String citySql = "INSERT INTO cities (countryname , cityname) VALUES (? , ?  );";
        try{
            connection = getDBConnection(dbURL, user, password);
            for(JsonModel jsonModel:jsonModels) {
                connection = getDBConnection(dbURL, user, password);
                preparedStatementCountry = connection.prepareStatement(countrySql);
                preparedStatementCountry.setString(1, jsonModel.getCountryName().toString() );
                preparedStatementCountry.setString(2,jsonModel.getCountryIsoCode().toString() );
                preparedStatementCountry.execute();
            }
            for(JsonModel jsonModel:jsonModels) {
                connection = getDBConnection(dbURL, user, password);
                preparedStatementCity = connection.prepareStatement(citySql);
                for (String city : jsonModel.getCities()) {
                    preparedStatementCity.setString(1, jsonModel.getCountryName().toString());
                    preparedStatementCity.setString(2, city.toString());
                    preparedStatementCity.execute();
                }
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }finally {

            if (preparedStatementCountry != null) {
                preparedStatementCountry.close();
            }

            if (preparedStatementCity != null) {
                preparedStatementCity.close();

            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
