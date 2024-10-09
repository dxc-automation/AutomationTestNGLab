package com.demo.properties;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;

import java.io.FileReader;
import java.util.Base64;

import static com.demo.properties.FilePaths.report_json_folder;

public class TestData {

    private String email;
    private String password;


    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    public void getTestData() {
        String json = reader();

        String encodedEmail = JsonPath.read(json, "$.Account.Email");
        setEmail(decode(encodedEmail));

        String encodedPassword = JsonPath.read(json, "$.Account.Password");
        setPassword(decode(encodedPassword));

        System.out.println("\n" + getEmail() + "\n" + getPassword());
    }


    public String reader() {
        try {
            Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
            JsonParser parser = new JsonParser();
            JsonObject object = parser.parse(new FileReader(FilePaths.test_data_file)).getAsJsonObject();
            String formattedJson = gson.toJson(object);
            return formattedJson;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String decode(String value) {
        byte[] decodedBytes = Base64.getDecoder().decode(value);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }
}
