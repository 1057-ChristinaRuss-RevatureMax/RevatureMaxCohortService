package controllers;


import models.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class apiUtils {
    public static ArrayList<Associate> JSONConvertAssociate(String paramBody) {
        ArrayList userList = new ArrayList<Associate>();
        JSONArray jsonArray = new JSONArray(paramBody);
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            userList.add(new Associate(
                    jsonObject.getString("firstName"),
                    jsonObject.getString("lastName"),
                    jsonObject.getString("email"),
                    jsonObject.getString("salesforceId")
            ));
        }
        return userList;
    }

    public static ArrayList<AssociateAssignment> JSONConvertAssociateAssignment(String paramBody) {
        ArrayList assignmentList = new ArrayList<AssociateAssignment>();
        JSONArray jsonArray = new JSONArray(paramBody);
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++){
            jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject);
            assignmentList.add(new AssociateAssignment(
                    jsonObject.getBoolean("active"),
                    jsonObject.getAssociate("associate"),
                    jsonObject.getBatch("batch"),
                    jsonObject.getString("endDate"),
                    jsonObject.getString("startDate"),
                    jsonObject.getString("trainingStatus")
            ));
        }
        return assignmentList;
    }

    public static ArrayList<AssociateDto> JSONConvertAssociateDto(String paramBody){
        ArrayList associateDtoList = new ArrayList<AssociateDto>();
        JSONArray jsonArray = new JSONArray(paramBody);
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++){
            jsonObject = jsonArray.getJSONObject(i);
            associateDtoList.add(new AssociateDto(
                    jsonObject.getString("batchId"),
                    jsonObject.getString("email"),
                    jsonObject.getFlagDto("flag"),
                    jsonObject.getString("id"),
                    jsonObject.getString("name"),
                    jsonObject.getString("trainingStatus")
            ));
        }
        return associateDtoList;
    }

    public static ArrayList<Batch> JSONConvertBatch(String paramBody){
        ArrayList batchList = new ArrayList<Batch>();
        JSONArray jsonArray = new JSONArray(paramBody);
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++){
            jsonObject = jsonArray.getJSONObject(i);
            batchList.add(new Batch(
                    jsonObject.getAssociateAssignment("associateAssignments"),
                    jsonObject.getString("batchId"),
                    jsonObject.getInt("currentWeek"),
                    jsonObject.getEmployeeAssignment("employeeAssignments"),
                    jsonObject.getString("endDate"),
                    jsonObject.getInt("goodGrade"),
                    jsonObject.getInt("id"),
                    jsonObject.getString("location"),
                    jsonObject.getString("name"),
                    jsonObject.getInt("passingGrade"),
                    jsonObject.getString("skill"),
                    jsonObject.getString("startDate"),
                    jsonObject.getString("type")
            ));
        }
        return batchList;
    }

    public static ArrayList<Employee> JSONConvertEmployee(String paramBody) {
        ArrayList employeeList = new ArrayList<Employee>();
        JSONArray jsonArray = new JSONArray(paramBody);
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            employeeList.add(new Employee(
                    jsonObject.getString("email"),
                    jsonObject.getString("firstName"),
                    jsonObject.getString("lastName"),
                    jsonObject.getEmployeeAssignment("trainingBatches")
            ));
        }
        return employeeList;
    }

    public static ArrayList<EmployeeAssignment> JSONConvertEmployeeAssignment(String paramBody) {
        ArrayList employeeAssignmentList = new ArrayList<EmployeeAssignment>();
        JSONArray jsonArray = new JSONArray(paramBody);
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            employeeAssignmentList.add(new EmployeeAssignment(
                    jsonObject.getBatch("batch"),
                    jsonObject.getString("deletedAt"),
                    jsonObject.getEmployee("employee"),
                    jsonObject.getString("role")
            ));
        }
        return employeeAssignmentList;
    }

    public static ArrayList<Flag> JSONConvertFlag(String paramBody){
        ArrayList flagList = new ArrayList<Flag>();
        JSONArray jsonArray = new JSONArray(paramBody);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        flagList.add(new Flag(
            jsonObject.getString("content"),
            jsonObject.getInt("id"),
            jsonObject.getString("type")
        ));
        return flagList;
    }

    public static ArrayList<FlagDto> JSONConvertFlagDto(String paramBody){
        ArrayList flagDtoList = new ArrayList<FlagDto>();
        JSONArray jsonArray = new JSONArray(paramBody);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        flagDtoList.add(new FlagDto(
            jsonObject.getString("associateEmail"),
            jsonObject.getString("content"),
            jsonObject.getInt("id"),
            jsonObject.getString("type")
        ));
        return flagDtoList;
    }
}