package config;
import org.json.JSONArray;
import org.json.JSONObject;
import dao.apiDAOimpl;
import controllers.apiUtils;
import models.Associate;
import models.Employee;

import java.util.ArrayList;
import dao.AssociateDaoImpl;
import dao.AssociatePortfolioDaoImpl;
import dao.EmployeeDaoImpl;
import dao.EmployeePortfolioDao;

public class ApiDatabaseConfig {

    public static void initAssociates(){

        AssociateDaoImpl dao = new AssociateDaoImpl();
        ArrayList<String> ids = dao.getAllSalesForceId();
        AssociatePortfolioDaoImpl portfoliodb = new AssociatePortfolioDaoImpl();

        for(String id : ids){
            portfoliodb.createOne(id);
        }
        
    }

    public static void initEmployees(){
        int id = 0;
        EmployeeDaoImpl eImpl = new EmployeeDaoImpl();
        EmployeePortfolioDao portfoliodb = new EmployeePortfolioDao();

        ArrayList employeeList = new ArrayList<Employee>();
        String parambody = apiDAOimpl.getAllEmployees();
        employeeList = apiUtils.JSONConvertEmployee(parambody);

        for(int i=0; i<employeeList.size(); i++){
            id++;
            JSONObject user = new JSONObject(employeeList.get(i));
            eImpl.createEmployee(id,user.getString("firstName"),user.getString("lastName"),user.getString("email"), "password");
            portfoliodb.createOne(id);
            
        }
    }
}