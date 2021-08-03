package controllers;


import models.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class apiUtils {
    public static ArrayList<Associate> JSONConvertAssociate(String paramBody) {
        ArrayList<Associate> userList = new ArrayList<Associate>();
        JSONArray jsonArray = new JSONArray(paramBody);
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            Associate associate = new Associate();
            associate.setEmail(jsonObject.getString("email"));
            associate.setFirstname(jsonObject.getString("firstName"));
//            associate.setFlag(parseSubFlag(jsonObject, "flag"));
            associate.setLastname(jsonObject.getString("lastName"));
            associate.setSalesforceId(jsonObject.getString("salesforceId"));
//            associate.setAssociateAssignments(parseSubAssociateAssignment(jsonObject, "trainingAssignments", false));
            userList.add(associate);
        }
        return userList;
    }

    public static ArrayList<AssociateAssignment> JSONConvertAssociateAssignment(String paramBody) {
        ArrayList<AssociateAssignment> assignmentList = new ArrayList<AssociateAssignment>();
        JSONArray jsonArray = new JSONArray(paramBody);
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++){
            jsonObject = jsonArray.getJSONObject(i);
            jsonObject.getJSONObject("associate");
            AssociateAssignment assocAssign = new AssociateAssignment();
            assocAssign.setActive(jsonObject.getBoolean("active"));
            assocAssign.setAssociate(parseSubAssociate(jsonObject, "associate", false));
            assocAssign.setBatch(parseSubBatch(jsonObject, "batch", false, true));
            assocAssign.setEndDate(jsonObject.getString("endDate"));
            assocAssign.setStartDate(jsonObject.getString("startDate"));
            assocAssign.setTrainingStatus(jsonObject.getString("trainingStatus"));
            assignmentList.add(assocAssign);
        }
        return assignmentList;
    }

    public static ArrayList<AssociateDto> JSONConvertAssociateDto(String paramBody){
        ArrayList<AssociateDto> associateDtoList = new ArrayList<AssociateDto>();
        JSONArray jsonArray = new JSONArray(paramBody);
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++){
            jsonObject = jsonArray.getJSONObject(i);
            AssociateDto associateDto = new AssociateDto();
            associateDto.setBatchId(jsonObject.getString("batchId"));
            associateDto.setEmail(jsonObject.getString("email"));
            associateDto.setFlag(parseSubFlagDto(jsonObject, "flag"));
            associateDto.setId(jsonObject.getString("id"));
            associateDto.setName(jsonObject.getString("name"));
            associateDto.setTrainingStatus(jsonObject.getString("trainingStatus"));
            associateDtoList.add(associateDto);
        }
        return associateDtoList;
    }

    public static ArrayList<Batch> JSONConvertBatch(String paramBody){
        ArrayList<Batch> batchList = new ArrayList<Batch>();
        JSONArray jsonArray = new JSONArray(paramBody);
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++){
            jsonObject = jsonArray.getJSONObject(i);
            Batch batch = new Batch();
            batch.setAssociateAssignments(parseSubAssociateAssignment(jsonObject, "associateAssignments", false));
            batch.setBatchId(jsonObject.getString("batchId"));
            batch.setCurrentWeek(jsonObject.getInt("currentWeek"));
            batch.setEmployeeAssignments(parseSubEmployeeAssignment(jsonObject, "employeeAssignments", false, true));
            batch.setEndDate(jsonObject.getString("endDate"));
            batch.setGoodGrade(jsonObject.getInt("goodGrade"));
            batch.setId(jsonObject.getInt("id"));
            batch.setLocation(jsonObject.getString("location"));
            batch.setName(jsonObject.getString("name"));
            batch.setPassingGrade(jsonObject.getInt("passingGrade"));
            batch.setSkill(jsonObject.getString("skill"));
            batch.setStartDate(jsonObject.getString("startDate"));
            batch.setType(jsonObject.getString("type"));
            batchList.add(batch);
        }
        return batchList;
    }

    public static ArrayList<Employee> JSONConvertEmployee(String paramBody) {
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        JSONArray jsonArray = new JSONArray(paramBody);
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++) {
            Employee employee = new Employee();
            jsonObject = jsonArray.getJSONObject(i);
            employee.setEmail(jsonObject.getString("email"));
            employee.setFirstName(jsonObject.getString("firstName"));
            employee.setLastName(jsonObject.getString("lastName"));
            employee.setTrainingBatches(parseSubEmployeeAssignment(jsonObject, "trainingBatches", false, false));
            employeeList.add(employee);
        }
        return employeeList;
    }

    public static ArrayList<EmployeeAssignment> JSONConvertEmployeeAssignment(String paramBody) {
        ArrayList<EmployeeAssignment> employeeAssignmentList = new ArrayList<EmployeeAssignment>();
        JSONArray jsonArray = new JSONArray(paramBody);
        JSONObject jsonObject;
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            EmployeeAssignment employeeAssignment = new EmployeeAssignment();
            employeeAssignment.setBatch(parseSubBatch(jsonObject, "batch", false, false));
            employeeAssignment.setDeletedAt(jsonObject.getString("deletedAt"));
            employeeAssignment.setEmployee(parseSubEmployee(jsonObject, "employee", false));
            employeeAssignment.setRole(jsonObject.getString("role"));
            employeeAssignmentList.add(employeeAssignment);
        }
        return employeeAssignmentList;
    }

    public static ArrayList<Flag> JSONConvertFlag(String paramBody){
        ArrayList<Flag> flagList = new ArrayList<Flag>();
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
        ArrayList<FlagDto> flagDtoList = new ArrayList<FlagDto>();
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

    //For parsing sub-objects
    private static Associate parseSubAssociate(JSONObject my_json, String key, Boolean assocAssign_present){
        JSONObject json_assoc =  my_json.getJSONObject(key);
        Associate associate = new Associate();
            associate.setEmail(json_assoc.getString("email"));
            associate.setFirstname(json_assoc.getString("firstname"));
            associate.setFlag(parseSubFlag(json_assoc, "flag"));
            associate.setLastname(json_assoc.getString("lastname"));
            associate.setSalesforceId(json_assoc.getString("salesforceId"));
            if (assocAssign_present) {
                associate.setAssociateAssignments(parseSubAssociateAssignment(json_assoc, "associateAssignments", false));
            }
        return associate;
    }

    private static AssociateAssignment[] parseSubAssociateAssignment(JSONObject my_json, String key, Boolean associate_present){
        JSONArray json_assocassignments =  my_json.getJSONArray(key);
        AssociateAssignment[] associateAssignmentsList = new AssociateAssignment[my_json.length()];
        for (int i=0; i < my_json.length(); i++){
            JSONObject json_assocassign = json_assocassignments.getJSONObject(i);
            AssociateAssignment entry = new AssociateAssignment();
            entry.setActive(json_assocassign.getBoolean("active"));
            if (associate_present){
                entry.setAssociate(parseSubAssociate(json_assocassign, "associate", false));
            }
            entry.setEndDate(json_assocassign.getString("endDate"));
            entry.setStartDate(json_assocassign.getString("startDate"));
            entry.setTrainingStatus(json_assocassign.getString("trainingStatus"));
            associateAssignmentsList[i] = entry;
            }
        return associateAssignmentsList;
    }

    private static Batch parseSubBatch(JSONObject my_json, String key, Boolean assocAssign_present, Boolean empAssign_present){
        JSONObject json_batch = my_json.getJSONObject(key);
        Batch batch = new Batch();
        if (assocAssign_present){
            batch.setAssociateAssignments(parseSubAssociateAssignment(json_batch, "associateAssignments", false));
        }
        batch.setBatchId(json_batch.getString("batchId"));
        batch.setCurrentWeek(json_batch.getInt("currentWeek"));
        if (empAssign_present){
            batch.setEmployeeAssignments(parseSubEmployeeAssignment(json_batch, "employeeAssignments", false, false));
        }
        batch.setEndDate(json_batch.getString("endDate"));
        batch.setGoodGrade(json_batch.getInt("goodGrade"));
        batch.setId(json_batch.getInt("id"));
        batch.setLocation(json_batch.getString("location"));
        batch.setName(json_batch.getString("name"));
        batch.setPassingGrade(json_batch.getInt("passingGrade"));
        batch.setSkill(json_batch.getString("skill"));
        batch.setStartDate(json_batch.getString("startDate"));
        batch.setType(json_batch.getString("type"));
        return batch;
    }

    private static EmployeeAssignment[] parseSubEmployeeAssignment(JSONObject my_json, String key, Boolean batch_present, Boolean employee_present){
        JSONArray json_employeeassignments =  my_json.getJSONArray(key);
        EmployeeAssignment[] employeeAssignmentsList = new EmployeeAssignment[my_json.length()];
        for (int i=0; i < my_json.length(); i++) {
            JSONObject json_employeeassign = json_employeeassignments.getJSONObject(i);
            EmployeeAssignment employeeAssignment = new EmployeeAssignment();
            if (batch_present) {
                employeeAssignment.setBatch(parseSubBatch(json_employeeassign, "batch", false, false));
            }
            employeeAssignment.setDeletedAt(json_employeeassign.getString("deletedAt"));
            if (employee_present) {
                employeeAssignment.setEmployee(parseSubEmployee(json_employeeassign, "employee", false));
            }
            employeeAssignment.setRole(json_employeeassign.getString("role"));
            employeeAssignmentsList[i] = employeeAssignment;
        }
        return employeeAssignmentsList;
    }

    private static Employee parseSubEmployee(JSONObject my_json, String key, Boolean employeeassignment_present){
        JSONObject json_employee = my_json.getJSONObject(key);
        Employee employee = new Employee();
        employee.setEmail(json_employee.getString("email"));
        employee.setFirstName(json_employee.getString("firstName"));
        employee.setLastName(json_employee.getString("lastname"));
        if (employeeassignment_present) {
            employee.setTrainingBatches(parseSubEmployeeAssignment(json_employee, "trainingBatches", false, false));
        }
        return employee;
    }

    private static FlagDto parseSubFlagDto(JSONObject my_json, String key){
        JSONObject json_flagDto = my_json.getJSONObject(key);
        FlagDto flagDto = new FlagDto(
                json_flagDto.getString("associateEmail"),
                json_flagDto.getString("content"),
                json_flagDto.getInt("id"),
                json_flagDto.getString("type")
        );
        return flagDto;
    }

    private static Flag parseSubFlag(JSONObject my_json, String key){
        JSONObject json_flag = my_json.getJSONObject(key);
        Flag flag = new Flag(
                json_flag.getString("content"),
                json_flag.getInt("id"),
                json_flag.getString("type")
        );
        return flag;
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