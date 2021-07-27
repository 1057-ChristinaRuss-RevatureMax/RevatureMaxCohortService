package controllers;


import org.json.JSONArray;
import org.json.JSONObject;
import controllers.Controller;

public class ControllerUtils {
    public static void JSONconvert(String paramBody, String key) {
        JSONArray jsonarray = new JSONArray(paramBody);
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            System.out.println(jsonobject.getString(key));
        }
    }
}