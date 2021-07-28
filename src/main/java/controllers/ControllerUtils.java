package controllers;


import org.json.JSONArray;
import org.json.JSONObject;
import user.Associate;

import java.util.ArrayList;

public class ControllerUtils {
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
}