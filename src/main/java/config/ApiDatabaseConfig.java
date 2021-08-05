package config;
import org.json.JSONArray;
import org.json.JSONObject;
import dao.apiDAOimpl;
import controllers.apiUtils;
import models.Associate;
import java.util.ArrayList;
import dao.AssociateDaoImpl;

public class ApiDatabaseConfig {

    public static void initAssociates(){
        AssociateDaoImpl db = new AssociateDaoImpl();
        ArrayList userList = new ArrayList<Associate>();
        String parambody = apiDAOimpl.getAllUsers();
        userList = apiUtils.JSONConvertAssociate(parambody);


        //void createOne(String salesforceId, String firstName, String lastName, String email, String pass_word);
        for(int i=0; i<userList.size(); i++){
            JSONObject user = new JSONObject(userList.get(i));
            db.createAssociate(user.getString("salesforceId"),user.getString("firstname"),user.getString("lastname"),user.getString("email"),"password");
        }
        
    }
}