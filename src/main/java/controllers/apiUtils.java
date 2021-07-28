package controllers;


import org.json.JSONArray;
import org.json.JSONObject;
import models.Associate;

import java.util.ArrayList;
import java.util.HashMap;

public class apiUtils {
    public static ArrayList<Associate> JSONconvert(String paramBody) {
        ArrayList userList = new ArrayList<Associate>();
        JSONArray jsonarray = new JSONArray(paramBody);
        JSONObject jsonobject;
        for (int i = 0; i < jsonarray.length(); i++) {
            jsonobject = jsonarray.getJSONObject(i);
            userList.add(new Associate(jsonobject.getString("firstName"), jsonobject.getString("lastName"), jsonobject.getString("email"), jsonobject.getString("salesforceId")));
        }
        return userList;
    }

    public static HashMap<String, String> mapFromJson(String json) {
        HashMap<String, String> paramMap = new HashMap<String, String>();

        if (json.length() > 2)
            json = json.substring(1, json.length() - 1);
        else
            return paramMap;

        String[] bodyList = json.split(",");
        for (int i = 0; i < bodyList.length; i++) {
            String[] params = bodyList[i].split(":");
            if (params.length > 1) {
                String key = params[0];
                String value = params[1];
                if (key.length() > 2 && value.length() > 2) {
                    key = key.substring(1, key.length() - 1);
                    value = value.substring(1, value.length() - 1);
                    paramMap.put(key, value);
                }
            }
        }
        return paramMap;
    }

}